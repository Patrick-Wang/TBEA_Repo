package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.controller.Controller;


public class CallControllerXmlInterpreter implements XmlInterpreter {
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCallController(e)){
			return false;
		}

		String id = e.getAttribute("id");
		
		Controller controller = null;
		if ("true".equals(e.getAttribute("inline"))){
			controller = component.getCM().createController(component, id, component.localContext());
		}else{
			controller = component.getCM().createController(component, id);
		}
		
		if (null != controller){
			controller.run(component.globalContext());
		}else{
			System.out.println("call controller " + e.toString() + " find failed");
		}
		return true;
	}
}