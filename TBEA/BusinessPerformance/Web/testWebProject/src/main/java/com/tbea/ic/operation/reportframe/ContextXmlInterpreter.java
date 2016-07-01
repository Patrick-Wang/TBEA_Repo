package com.tbea.ic.operation.reportframe;

import java.util.List;

import org.w3c.dom.Element;


public class ContextXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"context".equals(e.getTagName())){
			return false;
		}
		
		String key = e.getAttribute("key");
		String value = e.getAttribute("value");
		ELParser elp = new ELParser(component);
		List<ELExpression> elps = elp.parser(value);
		if (!elps.isEmpty()){
			try {
				component.global(key, elps.get(0).value());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			component.global(key, component.getVar(value));
		}
		return true;
	}
}