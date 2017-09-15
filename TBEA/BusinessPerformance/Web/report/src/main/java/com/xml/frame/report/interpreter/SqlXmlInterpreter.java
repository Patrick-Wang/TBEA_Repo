package com.xml.frame.report.interpreter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.w3c.dom.Element;

import com.frame.script.el.ELExpression;
import com.frame.script.el.ELParser;
import com.frame.script.util.StringUtil;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.service.JndiDataSourceFactory;
import com.xml.frame.report.component.service.JpaTransaction;
import com.xml.frame.report.util.DBUtil;
import com.xml.frame.report.util.LoggerProxy;
import com.xml.frame.report.util.Pair;
import com.xml.frame.report.util.xml.XmlUtil;

import net.sf.json.JSONArray;
public class SqlXmlInterpreter implements XmlInterpreter {


	boolean isInWhereClause(String sqlPrefix){
		String lower = sqlPrefix.toLowerCase();
		int index = lower.lastIndexOf("where");
		if (index >= 0){
			return lower.indexOf("select", index) < 0;
		}
		return false;
	}
	
	ELParser el;
	
	
	Pair<String, List<Pair<Integer, Object>>> parseSqlParam(String sql){
		List<ELExpression> elexps = el.parser(sql);
		List<Pair<Integer, Object>> params = new ArrayList<Pair<Integer, Object>>();
		for (int i = elexps.size() - 1; i >= 0 ; --i){
			try {
				lp.trace("exp : " + elexps.get(i).exp());
				Object obj = elexps.get(i).value();
				String preFix = sql.substring(0, elexps.get(i).start());
				if (isInWhereClause(preFix)){
					sql = preFix + "?" + i + sql.substring(elexps.get(i).end());
					params.add(new Pair<Integer, Object>(i, obj));
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
	
	Query jpaQuery(Pair<String, List<Pair<Integer, Object>>> sqlParam, EntityManager em){
		Query q = em.createNativeQuery(sqlParam.getFirst());
		for (Pair<Integer, Object> pair : sqlParam.getSecond()){
			q.setParameter(pair.getFirst(), pair.getSecond());
			lp.info("?" + pair.getFirst() + " : " + pair.getSecond() + "\t");
		}

		return q;
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
	
	LoggerProxy lp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isSql(e)){
			return false;
		}
		
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		
		String trans = component.getConfigAttribute("transaction");
		JpaTransaction tx = (JpaTransaction) component.getVar(trans);
		DataSource ds = null;
		if (null == tx){
			if (null == tx){
				ds = JndiDataSourceFactory.getDataSource(component.getConfigAttribute("ds"));
				if (null == ds) {
					throw new Exception("请指定 transaction " + e.toString());
				}
			}
		}

		lp = new LoggerProxy();
		if (e.hasAttribute("logger")){
			lp.getLogger(e.getAttribute("logger"));
		}else{
			lp.getLogger("SQL");
		}
		
		lp.info("database : " + trans);
		
		el = new ELParser(component);
		String sqlText = XmlUtil.getText(e);
		if (XmlUtil.hasText(e) && !StringUtil.trim(sqlText).isEmpty()){
			Pair<String, List<Pair<Integer, Object>>> sqlParams = parseSqlParam(sqlText);
			if (tx != null) {
				Query q = jpaQuery(sqlParams, tx.getEntityManager());
				if (e.hasAttribute("id")){
					List sqlRet = q.getResultList();
					int retType = DBUtil.trans2StandardType(sqlRet);
					if (retType != DBUtil.SQL_RET_VALUE){
						sqlRet = parseOrder(sqlRet, component, e, null);
					}
					lp.info(JSONArray.fromObject(sqlRet).toString());
					component.put(e, sqlRet);
				}else{
					q.executeUpdate();
				}
			}else {
				PreparedStatement ps = jdbcQuery(sqlParams, ds);
				if (e.hasAttribute("id")){
					ResultSet rs = ps.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					int count = rsmd.getColumnCount();
					e.setAttribute("colcount", "" + count);
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						List<Object> row = new ArrayList<Object>();
						for (int i = 0; i < count; ++i) {
							row.add(DBUtil.transform(rs.getObject(i)));
						}
						result.add(row);
					}
					if (result.size() == 1 && ((List)(result.get(0))).size() == 1) {
						result = (List<Object>) result.get(0);
					}
				}else {
					ps.executeUpdate(sqlParams.getFirst());
				}
			}
		}else{
			List sqlRet = (List) component.getVar(e.getAttribute("id"));
			sqlRet = parseOrder(sqlRet, component, e, null);
			lp.info(JSONArray.fromObject(sqlRet).toString());
			component.put(e, sqlRet);
		}
		
		return true;
	}

	private PreparedStatement jdbcQuery(Pair<String, List<Pair<Integer, Object>>> sqlParams, DataSource ds) throws SQLException {
		PreparedStatement ps = ds.getConnection().prepareStatement(sqlParams.getFirst());
		for (Pair<Integer, Object> pair : sqlParams.getSecond()){
			ps.setObject(pair.getFirst(), pair.getSecond());
			lp.info("?" + pair.getFirst() + " : " + pair.getSecond() + "\t");
		}
		return ps;
	}
}