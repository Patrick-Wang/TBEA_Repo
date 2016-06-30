package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Controller extends AbstractXmlComponent {

	Service service;
	
	XmlInterpreter[] interpreters = new XmlInterpreter[]{
			new ExcelTemplateXmlInterpreter(),
			new FormatterServerXmlInterpreter(),
			new FormatterXmlInterpreter(),
			new ResponseXmlInterpreter()
	};
	
	public void setService(Service service){
		this.service = service;
	}
	
	public Controller(Element e) {
		super(e);
	}
	
	public void setConfig(Element config){
		this.config = config;
	}

	public String getServiceName(){
		return config.getAttribute("service");
	}
	
	public String getUrl() {
		return config.getAttribute("url");
	}
	
	@Override
	protected void onRun() {
		if (null != service){
			service.run(this.global);
		}
		
		NodeList children = this.config.getChildNodes();
		
		for (int i = 0; i < children.getLength(); ++i){
			if (children.item(i) instanceof Element){
				Element e = (Element)children.item(i);
				for (XmlInterpreter interpreter : interpreters){
					if (interpreter.accept(this, e)){
						break;
					}
				}
			}
		}
	}



}
