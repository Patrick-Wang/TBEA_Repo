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


public class SqlXmlInterpreter implements XmlInterpreter {

	List parseElSql(ELParser el, String sql, EntityManager em){
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
		
		sql = sql.replaceAll("\\s+", " ");
		System.out.println(sql);
		
		Query q = em.createNativeQuery(sql);
		for (int i = 0; i < objs.size(); ++i){
			q.setParameter(i, objs.get(i));
			System.out.print("?" + i + " : " + objs.get(i) + "\t");
		}
		
		System.out.println(" ");
		return q.getResultList();
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
		
		List sqlRet = parseElSql(
				new ELParser(component), 
				e.getFirstChild().getTextContent(),
				tx.getEntityManager());
		
		if (!sqlRet.isEmpty()){
			if (sqlRet.get(0).getClass().isArray()){
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
		
		component.put(e, sqlRet);
		return true;
	}
}