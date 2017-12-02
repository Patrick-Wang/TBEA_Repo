package com.xml.frame.report.interpreter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.w3c.dom.Element;

import com.frame.script.el.ELExpression;
import com.frame.script.el.ELParser;
import com.util.tools.Pair;
import com.util.tools.StringUtil;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.manager.ComponentManager;
import com.xml.frame.report.component.service.JpaTransaction;
import com.xml.frame.report.util.DBUtil;
import com.xml.frame.report.util.LoggerProxy;
import com.xml.frame.report.util.xml.XmlUtil;

import net.sf.json.JSONArray;
public class SqlXmlInterpreter implements XmlInterpreter {



	
	ELParser el;
	LoggerProxy lp;
	
	boolean isInWhereClause(String sqlPrefix){
		String lower = sqlPrefix.toLowerCase();
		int index = lower.lastIndexOf("where");
		if (index >= 0){
			return lower.indexOf("select", index) < 0;
		}
		return false;
	}


	Pair<String, List<Pair<Integer, Object>>> parseSqlParam(String sql, boolean isJpa){
		List<ELExpression> elexps = el.parser(sql);
		List<Pair<Integer, Object>> params = new ArrayList<Pair<Integer, Object>>();
		for (int i = elexps.size() - 1; i >= 0 ; --i){
			try {
				lp.trace("exp : " + elexps.get(i).exp());
				Object obj = elexps.get(i).value();
				String preFix = sql.substring(0, elexps.get(i).start());
				if (isInWhereClause(preFix)){
					if (!isJpa) {
						if (obj instanceof List){
							List ls = (List)obj;
							StringBuilder sb = new StringBuilder();
							sb.append("(?");
                            params.add(new Pair<Integer, Object>(0, ls.get(0)));
							for (int j = 1; j < ls.size(); ++j){
                                sb.append(",?");
                                params.add(new Pair<Integer, Object>(0, ls.get(j)));
							}
							sb.append(")");
							sql = preFix + sb.toString() + sql.substring(elexps.get(i).end());
						}else{
							sql = preFix + "?" + sql.substring(elexps.get(i).end());
							params.add(new Pair<Integer, Object>(0, obj));
						}
					}else {
						sql = preFix + "?" + i + sql.substring(elexps.get(i).end());
						params.add(new Pair<Integer, Object>(i, obj));
					}

				}else{
					sql = preFix + obj + sql.substring(elexps.get(i).end());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		lp.info(sql);
		return new Pair<String, List<Pair<Integer, Object>>>(sql, params);
	}
	
	Query setQueryParam(Pair<String, List<Pair<Integer, Object>>> sqlParam, EntityManager em){
		Query q = em.createNativeQuery(sqlParam.getFirst());
		for (Pair<Integer, Object> pair : sqlParam.getSecond()){
			lp.info("?" + pair.getFirst() + " : " + pair.getSecond() + "\t");
			q.setParameter(pair.getFirst(), pair.getSecond());
		}

		return q;
	}


	private PreparedStatement setQueryParam(Pair<String, List<Pair<Integer, Object>>> sqlParams, Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sqlParams.getFirst(),ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for (int i = 0, len = sqlParams.getSecond().size(); i < len; ++i){
			Pair<Integer, Object> pair = sqlParams.getSecond().get(i);
			lp.info("?" + (len - i) + " : " + pair.getSecond() + "\t");
            ps.setObject(len - i, pair.getSecond());
		}
		return ps;
	}
	
	private int find(List<Object[]> sqlRet, Object val, int by){
		for (int j = 0; j < sqlRet.size(); ++j){
			if (val.equals(sqlRet.get(j)[by])){
				return j;
			}
		}
		return -1;
	} 
	
	private List parseOrder(List sqlRet, AbstractXmlComponent component, Element e, Integer colcount) throws Exception{
		List order = (List) component.getVar(e.getAttribute("order"));
		if (null != order){
			Integer by = XmlUtil.getIntAttr(e, "by", el, null);
			if (null == colcount) {
				colcount = XmlUtil.getIntAttr(e, "colcount", el, null);
			}
			if (null != by && null != colcount){
				List<Object[]> objs = new ArrayList<Object[]>(order.size());
				int ret = 0;
				for (int i = 0; i < order.size(); ++i){
					ret = find(sqlRet, order.get(i), by);
					if (ret >= 0){
						objs.add((Object[]) sqlRet.get(ret));
					}else{
						Object[] objArr = new Object[colcount];
						objArr[by] = order.get(i);
						objs.add(objArr);
					}
				}
				sqlRet = objs;
			}
		}
		return sqlRet;
	}

	
	void updateLogger(Element e) {
		lp = new LoggerProxy();
		if (e.hasAttribute("logger")){
			lp.getLogger(e.getAttribute("logger"));
		}else{
			lp.getLogger("SQL");
		}
	}
	
	void exeJpaQuery(AbstractXmlComponent component, Element e, JpaTransaction tx, Pair<String, List<Pair<Integer, Object>>> sqlParams, int pgSize, int pgNum) throws Exception{
		Query q = setQueryParam(sqlParams, tx.getEntityManager());
		if (e.hasAttribute("id")){
			if (pgSize > 0 && pgNum >= 0) {
				q.setFirstResult(pgNum * pgSize);
				q.setMaxResults(pgSize);
			}
			List sqlRet = q.getResultList();
			int retType = DBUtil.trans2StandardType(sqlRet);
			if (retType != DBUtil.SQL_RET_VALUE){
				sqlRet = parseOrder(sqlRet, component, e, null);
			}
			lp.info(sqlRet, 15);
			component.put(e, sqlRet);
		}else{
			q.executeUpdate();
		}
	}
	
	void exeDsQuery(AbstractXmlComponent component, Element e, DataSource ds, Pair<String, List<Pair<Integer, Object>>> sqlParams, int pgSize, int pgNum) throws Exception{
		Connection con = ds.getConnection();
		PreparedStatement ps = setQueryParam(sqlParams, con);
		if (e.hasAttribute("id")){
			if (pgSize > 0 && pgNum >= 0) {
				ps.setMaxRows((pgNum + 1) * pgSize);
			}
			ResultSet rs = ps.executeQuery();
			if (pgSize > 0 && pgNum >= 0) {
				rs.absolute(pgNum * pgSize);
			}
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			e.setAttribute("colcount", "" + count);
			List<Object> sqlRet = new ArrayList<Object>();
			while (rs.next()) {
				List<Object> row = new ArrayList<Object>(count);
				for (int i = 1; i <= count; ++i) {
					row.add(DBUtil.jdbcTransform(rs.getObject(i)));
				}
				sqlRet.add(row);
			}
			if (sqlRet.size() == 1 && ((List)(sqlRet.get(0))).size() == 1) {
				sqlRet = (List<Object>) sqlRet.get(0);
			}else {
				sqlRet = parseOrder(sqlRet, component, e, null);
			}
			lp.info(sqlRet, 15);
			component.put(e, sqlRet);
		}else {
			ps.executeUpdate();
		}
		con.close();
		con = null;
	}
	
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isSql(e)){
			return false;
		}
		
		updateLogger(e);
		el = new ELParser(component);
		String sqlText = XmlUtil.getText(e);
		if (null != sqlText && !StringUtil.trim(sqlText).isEmpty()){
			String dsName = component.getConfigAttribute("transaction");
			JpaTransaction tx = (JpaTransaction) component.getVar(dsName);
			DataSource ds = null;
			if (null == tx){
				dsName = component.getConfigAttribute("ds");
				ds = ComponentManager.getInstance().getDataSourceFactory().getDataSource(dsName);
				if (null == ds) {
					throw new Exception("请指定 transaction " + e.toString());
				}
			}

			lp.info("database : " + dsName);
			
			Pair<String, List<Pair<Integer, Object>>> sqlParams = parseSqlParam(sqlText, (tx != null));
			int pgSize = XmlUtil.getIntAttr(e, "pgSize", el, -1);
			int pgNum = XmlUtil.getIntAttr(e, "pgNum", el, -1);
			if (tx != null) {
				exeJpaQuery(component, e, tx, sqlParams, pgSize, pgNum);
			}else {
				exeDsQuery(component, e, ds, sqlParams, pgSize, pgNum);
			}
		}else{
			List sqlRet = (List) component.getVar(e.getAttribute("id"));
			sqlRet = parseOrder(sqlRet, component, e, null);
			lp.info(sqlRet, 15);
			component.put(e, sqlRet);
		}
		
		
		return true;
	}

}