package com.tbea.ic.operation.reportframe.interpreter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.sf.json.JSONArray;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.reportframe.el.ELExpression;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.StringUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
public class SqlXmlInterpreter implements XmlInterpreter {

	
	private final static int SQL_RET_TABLE = 0;
	private final static int SQL_RET_VALUE = 1;
	private final static int SQL_RET_EMPTY = 2;
	
	boolean isInWhereClause(String sqlPrefix){
		String lower = sqlPrefix.toLowerCase();
		int index = lower.lastIndexOf("where");
		if (index >= 0){
			return lower.indexOf("select", index) < 0;
		}
		return false;
	}
	
	ELParser el;
	Query parseElSql(String sql, EntityManager em){
		List<ELExpression> elexps = el.parser(sql);
		List<Pair<Integer, Object>> objs = new ArrayList<Pair<Integer, Object>>();
		for (int i = elexps.size() - 1; i >= 0 ; --i){
			try {
				ReportLogger.trace().debug("exp : {}", elexps.get(i).exp());
				Object obj = elexps.get(i).value();
				String preFix = sql.substring(0, elexps.get(i).start());
				if (isInWhereClause(preFix)){
					sql = preFix + "?" + i + sql.substring(elexps.get(i).end());
					objs.add(new Pair(i, obj));
				}else{
					sql = preFix + obj + sql.substring(elexps.get(i).end());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		ReportLogger.logger().debug(sql);
		
		Query q = em.createNativeQuery(sql);
		for (Pair<Integer, Object> pair : objs){
			q.setParameter(pair.getFirst(), pair.getSecond());
			ReportLogger.logger().debug("?{} : {}\t",pair.getFirst() , pair.getSecond());
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

	private Object transform(Object obj) {
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				obj = ((BigDecimal) obj).doubleValue();
			} else if (obj instanceof Long) {
				obj = ((Long) obj).intValue();
			} else if (obj instanceof BigInteger) {
				obj = ((BigInteger) obj).intValue();
			} else if (obj instanceof Date) {
				obj = new java.util.Date(((Date) obj).getTime());
			}
		}
		return obj;
	}

	private int typeTransform(List sqlRet) {
		if (!sqlRet.isEmpty()) {
			if (null != sqlRet.get(0) && sqlRet.get(0).getClass().isArray()) {
				for (Object[] objs : (List<Object[]>) sqlRet) {
					for (int i = objs.length - 1; i >= 0; --i) {
						objs[i] = transform(objs[i]);
					}
				}
				return SQL_RET_TABLE;
			} else {
				for (int i = sqlRet.size() - 1; i >= 0; --i) {
					sqlRet.set(i, transform(sqlRet.get(i)));
				}
				return SQL_RET_VALUE;
			}
		}
		return SQL_RET_EMPTY;
	}
	
	private List parseOrder(List sqlRet, AbstractXmlComponent component, Element e) throws Exception{
		List order = (List) component.getVar(e.getAttribute("order"));
		if (null != order){
			Integer by = XmlUtil.getIntAttr(e, "by", el, null);
			Integer colcount = XmlUtil.getIntAttr(e, "colcount", el, null);
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
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isSql(e)){
			return false;
		}
		
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		
		String trans = component.getConfigAttribute("transaction");
		Transaction tx = (Transaction) component.getVar(trans);
		if (null == tx){
			throw new Exception("请指定 transaction " + e.toString());
		}
		
		ReportLogger.trace().debug("database : {}", trans);
		
		el = new ELParser(component);
		if (XmlUtil.hasText(e) && !StringUtil.trim(XmlUtil.getText(e)).isEmpty()){
			Query q = parseElSql(
					XmlUtil.getText(e),
					tx.getEntityManager());
			if (e.hasAttribute("id")){
				List sqlRet = q.getResultList();
//				QueryImpl sq = q.unwrap(QueryImpl.class);
//				String[] alis = sq.getHibernateQuery().getReturnAliases();
//				List<NativeSQLQueryReturn> sr = sq.getQueryReturns();
//				sq.setResultTransformer()
//				String[] alis = sq.getReturnAliases();
//				Type[] tps = sq.getHibernateQuery().getReturnTypes();
				int retType = typeTransform(sqlRet);
				if (retType != SQL_RET_VALUE){
					sqlRet = parseOrder(sqlRet, component, e);
				}
				if (ReportLogger.logger().isDebugEnabled()){
					ReportLogger.logger().debug(JSONArray.fromObject(sqlRet).toString());
				}
				component.put(e, sqlRet);
			}else{
				q.executeUpdate();
			}
		}else{
			List sqlRet = (List) component.getVar(e.getAttribute("id"));
			sqlRet = parseOrder(sqlRet, component, e);
			if (ReportLogger.logger().isDebugEnabled()){
				ReportLogger.logger().debug(JSONArray.fromObject(sqlRet).toString());
			}
			component.put(e, sqlRet);
		}
		
		return true;
	}
}