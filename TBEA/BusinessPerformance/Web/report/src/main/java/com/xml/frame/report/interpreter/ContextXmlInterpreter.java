package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.XmlUtil;


public class ContextXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (Schema.isContext(e)){
			if (e.hasAttribute("key")){
				String value = null;
				if (e.hasAttribute("value")){
					value = e.getAttribute("value");
				}else{
					value = XmlUtil.getText(e);
				}
				
				Object obj = XmlUtil.parseELText(value, new ELParser(component));
				String key = e.getAttribute("key");
				
				if ("false".equals(e.getAttribute("export"))){
					if (null != obj){
						component.local(key, obj);
					}else{
						component.local(key, component.getVar(value));
					}
				}
				else{
					if (null != obj){
						component.global(key, obj);
					}else{
						component.global(key, component.getVar(value));
					}
				}
			}
			
		
			return true;
		}
		return false;
	}
}