package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.reportframe.XmlUtil.OnLoop;


public class Controller extends AbstractXmlComponent {

	public static final String MODEL_AND_VIEW = "_mv_";
	
	public Controller(Element e, ComponentManager mgr) {
		super(e, mgr);
	}

	XmlInterpreter[] interpreters = new XmlInterpreter[]{
			new ExcelTemplateXmlInterpreter(),
			new FormatterServerXmlInterpreter(),
			new FormatterXmlInterpreter(),
			new ResponseXmlInterpreter(),
			new ContextXmlInterpreter(),
			new CallServiceXmlInterpreter(),
			new ListXmlInterpreter()
	};
	
	
	
	public void setConfig(Element config){
		this.config = config;
	}
	
	public String getUrl() {
		return config.getAttribute("url");
	}
	
	@Override
	protected void onRun() {		
		NodeList children = this.config.getChildNodes();
		XmlUtil.each(children, new OnLoop(){
			@Override
			public void on(Element elem) {
				for (XmlInterpreter interpreter : interpreters){
					if (interpreter.accept(Controller.this, elem)){
						break;
					}
				}
			}
			
		});
	}

}
