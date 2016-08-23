package com.tbea.ic.operation.reportframe.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.w3c.dom.Element;

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
		
		System.out.println(sql.replaceAll("\\s+", " "));
		
		Query q = em.createNativeQuery(sql);
		for (int i = 0; i < objs.size(); ++i){
			q.setParameter(i, objs.get(i));
			System.out.print("?" + i + " : " + objs.get(i) + "\t");
		}
		
		System.out.println(" ");
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
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isSql(e)){
			return false;
		}

		Transaction tx = (Transaction) component.getVar(component.getConfig().getAttribute("transaction"));
		if (null == tx){
			throw new Exception("请指定 transaction " + e.toString());
		}
		el = new ELParser(component);
		Query q = parseElSql(
				e.getFirstChild().getTextContent(),
				tx.getEntityManager());
		
		if (!e.hasAttribute("id")){
			q.executeUpdate();
			return true;
		}
		
		List sqlRet = q.getResultList();
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
		component.put(e, sqlRet);
		return true;
	}
}