package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;


public class ContextXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"context".equals(e.getTagName())){
			return false;
		}
		
		String key = e.getAttribute("key");
		String value = e.getAttribute("value");
		component.global(key, component.getVar(value));
		return true;
	}
}