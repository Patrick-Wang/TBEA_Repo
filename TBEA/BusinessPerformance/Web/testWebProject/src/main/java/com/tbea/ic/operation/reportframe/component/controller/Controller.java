package com.tbea.ic.operation.reportframe.component.controller;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.interpreter.CallControllerXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.CallServiceXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.CallXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ContextXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ExcelTemplateXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.FormatterServerXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.FormatterXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.IFXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ListXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.LogXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.LoopXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ResponseXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.XmlInterpreter;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;


public class Controller extends AbstractXmlComponent {

	public static final String MODEL_AND_VIEW = "_mv_";
	
	public Controller(AbstractXmlComponent preComponent, Element e, ComponentManager mgr) {
		super(preComponent, e, mgr);
	}

	public Controller(AbstractXmlComponent preComponent, Element e, ComponentManager mgr,
			Context local) {
		super(preComponent, e, mgr);
		this.local = local;
	}

	XmlInterpreter[] interpreters = new XmlInterpreter[]{
			new CallControllerXmlInterpreter(),
			new ExcelTemplateXmlInterpreter(),
			new FormatterServerXmlInterpreter(),
			new FormatterXmlInterpreter(),
			new ResponseXmlInterpreter(),
			new ContextXmlInterpreter(),
			new CallServiceXmlInterpreter(),
			new ListXmlInterpreter(),
			new CallXmlInterpreter(),
			new IFXmlInterpreter(),
			new LoopXmlInterpreter(),
			new LogXmlInterpreter()
	}; 
	
	
	
	public void setConfig(Element config){
		this.config = config;
	}
	
	public String getUrl() {
		return config.getAttribute("url");
	}
	
	@Override
	protected void onRun() throws Exception {		
		XmlUtil.eachChildren(this.config, new OnLoop(){
			@Override
			public void on(Element elem) throws Exception  {
				for (XmlInterpreter interpreter : interpreters){
					if (interpreter.accept(Controller.this, elem)){
						if (ReportLogger.trace().isDebugEnabled()){
							ReportLogger.trace().debug("controller : " + XmlUtil.toStringFromDoc(elem));
						}
						break;
					}
				}
			}
		});
	}

	@Override
	public AbstractXmlComponent clone(Element e) {
		Controller controller = new Controller(preComponent, e, mgr);
		controller.local = this.local;
		controller.global = this.global;
		return controller;
	}

}
