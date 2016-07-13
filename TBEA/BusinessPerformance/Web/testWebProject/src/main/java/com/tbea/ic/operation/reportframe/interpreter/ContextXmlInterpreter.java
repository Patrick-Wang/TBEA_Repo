package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;


public class ContextXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (Schema.isContext(e)){
			String key = e.getAttribute("key");
			String value = e.getAttribute("value");
			Object obj = XmlUtil.getELValue(value, new ELParser(component));
			if (null != obj){
				component.global(key, obj);
			}else{
				component.global(key, component.getVar(value));
			}
			return true;
		}
		return false;
	}
}