package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.service.Service;


public class CallServiceXmlInterpreter implements XmlInterpreter {
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCallService(e)){
			return false;
		}
		
		String id = e.getAttribute("id");
		
		Service serv = null;
		if ("true".equals(e.getAttribute("inline"))){
			serv = component.getCM().createService(component, id, component.localContext());
		}else{
			serv = component.getCM().createService(component, id);
		}
		
		
		if (null != serv){
			if (e.hasAttribute("transaction") && !serv.getConfig().hasAttribute("transaction")){
				serv.getConfig().setAttribute("transaction", e.getAttribute("transaction"));
			}
			serv.run(component.globalContext());
		}else{
			System.out.println("call service " + e.getAttribute("id") + " find failed");
		}
		
		return true;
	}
}