package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.service.Service;


public class CallServiceXmlInterpreter implements XmlInterpreter {
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCallService(e)){
			return false;
		}

		String id = e.getAttribute("id");
		
		Service serv = null;
		if ("true".equals(e.getAttribute("inline"))){
			serv = component.getCM().createService(id, component.localContext());
		}else{
			serv = component.getCM().createService(id);
		}
		
		if (null != serv){
			serv.run(component.globalContext());
		}else{
			System.out.println("call service " + e.getAttribute("id") + " find failed");
		}
		return true;
	}
}