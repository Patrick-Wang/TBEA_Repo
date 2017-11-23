package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.controller.Controller;


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
			System.out.println("call controller " + id + " find failed");
		}
		return true;
	}
}