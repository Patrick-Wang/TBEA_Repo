package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;


public class ContextXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (Schema.isContext(e)){
			//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
			String key = e.getAttribute("key");
			String value = e.getAttribute("value");
			Object obj = XmlUtil.parseELText(value, new ELParser(component));
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
			return true;
		}
		return false;
	}
}