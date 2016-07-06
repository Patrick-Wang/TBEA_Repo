package com.tbea.ic.operation.reportframe.interpreter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.el.ELExpression;
import com.tbea.ic.operation.reportframe.el.ELParser;


public class SqlXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!Schema.isSql(e)){
			return false;
		}
		
		String sql = e.getFirstChild().getTextContent();
		String id = e.getAttribute("id");
		ELParser el = new ELParser(component);
		List<ELExpression> elexps = el.parser(sql);
		EntityManager em = (EntityManager) component.getVar(component.getConfig().getAttribute("db"));
		List<Object> objs = new ArrayList<Object>();
		for (int i = elexps.size() - 1; i >= 0; --i){
			try {
				Object obj = elexps.get(i).value();
				objs.add(0, obj);
				sql = sql.substring(0, elexps.get(i).start()) + "?" + i + sql.substring(elexps.get(i).end());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		Query q = em.createNativeQuery(sql);
		for (int i = 0; i < objs.size(); ++i){
			q.setParameter(i, objs.get(i));
		}
		List<Object[]> ret = q.getResultList();
		
		for (Object[] obs : ret){
			for (int i = 0; i < obs.length; ++i){
				if (obs[i] instanceof BigDecimal){
					obs[i] = ((BigDecimal)obs[i]).doubleValue();
				}else if (obs[i] instanceof Long){
					obs[i] = ((Long)obs[i]).intValue();
				}
			}
		}
		
		component.local(id, ret);
		return true;
	}
}