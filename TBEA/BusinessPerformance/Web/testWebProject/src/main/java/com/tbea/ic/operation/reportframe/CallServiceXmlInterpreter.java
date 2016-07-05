package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;


public class CallServiceXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"callservice".equals(e.getTagName())){
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