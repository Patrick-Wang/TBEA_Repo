package com.tbea.ic.operation.reportframe.interpreter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.tbea.ic.operation.reportframe.util.XmlUtil;

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
	Query parseElSql(String sql, EntityManager em){
		List<ELExpression> elexps = el.parser(sql);
		List<Pair<Integer, Object>> objs = new ArrayList<Pair<Integer, Object>>();
		for (int i = elexps.size() - 1; i >= 0 ; --i){
			try {
				ReportLogger.logger().debug("exp : {}", elexps.get(i).exp());
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
	
	private void preHandleResult(List sqlRet){
		if (!sqlRet.isEmpty() &&
				null != sqlRet.get(0) &&
				sqlRet.get(0).getClass().isArray()){
				for (Object[] objs : (List<Object[]>)sqlRet){
					for (int i = 0; i < objs.length; ++i){
						if (objs[i] instanceof BigDecimal){
							objs[i] = ((BigDecimal)objs[i]).doubleValue();
						} else if (objs[i] instanceof Long){
							objs[i] = ((Long)objs[i]).intValue();
						} else if (objs[i] instanceof Date){
							objs[i] = new java.util.Date(((Date)objs[i]).getTime());
						}
					}
				}
			}
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
						objs.add(new Object[colcount]);
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
		
		ReportLogger.logger().debug("database : {}", trans);
		
		el = new ELParser(component);
		
		Query q = parseElSql(
				e.getFirstChild().getTextContent(),
				tx.getEntityManager());
		
		if (e.hasAttribute("id")){
			
			List sqlRet = q.getResultList();

			preHandleResult(sqlRet);

			sqlRet = parseOrder(sqlRet, component, e);

			if (ReportLogger.logger().isDebugEnabled()){
				ReportLogger.logger().debug(JSONArray.fromObject(sqlRet).toString());
			}

			component.put(e, sqlRet);
			
		}else{
			q.executeUpdate();
		}

		return true;
	}
}