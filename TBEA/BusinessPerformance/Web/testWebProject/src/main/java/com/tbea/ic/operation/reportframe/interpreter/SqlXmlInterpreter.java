package com.tbea.ic.operation.reportframe.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.sf.json.JSONArray;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.reportframe.el.ELExpression;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;

public class SqlXmlInterpreter implements XmlInterpreter {

	ELParser el;
	Query parseElSql(String sql, EntityManager em){
		List<ELExpression> elexps = el.parser(sql);
		List<Object> objs = new ArrayList<Object>();
		for (int i = elexps.size() - 1; i >= 0; --i){
			try {
				Object obj = elexps.get(i).value();
				objs.add(0, obj);
				sql = sql.substring(0, elexps.get(i).start()) + "?" + i + sql.substring(elexps.get(i).end());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		ReportLogger.logger().debug(sql);
		
		Query q = em.createNativeQuery(sql);
		for (int i = 0; i < objs.size(); ++i){
			q.setParameter(i, objs.get(i));
			ReportLogger.logger().debug("?{} : {}\t",i , objs.get(i));
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
						}else if (objs[i] instanceof Long){
							objs[i] = ((Long)objs[i]).intValue();
						}
					}
				}

			}
	}
	
	private void parseOrder(List sqlRet, AbstractXmlComponent component, Element e) throws Exception{
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
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isSql(e)){
			return false;
		}
		
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

			parseOrder(sqlRet, component, e);

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