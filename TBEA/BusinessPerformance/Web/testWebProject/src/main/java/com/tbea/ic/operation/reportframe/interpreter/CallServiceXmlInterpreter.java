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
		Service serv = component.getCM().getService(id);
		if (null != serv){
			serv.run(component.globalContext());
		}
		return true;
	}
}