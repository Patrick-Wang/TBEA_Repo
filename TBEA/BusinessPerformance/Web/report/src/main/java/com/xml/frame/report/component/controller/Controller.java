package com.xml.frame.report.component.controller;

import org.w3c.dom.Element;

import com.util.tools.xml.Loop;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.manager.ComponentManager;
import com.xml.frame.report.interpreter.CallControllerXmlInterpreter;
import com.xml.frame.report.interpreter.CallServiceXmlInterpreter;
import com.xml.frame.report.interpreter.CallXmlInterpreter;
import com.xml.frame.report.interpreter.ContextXmlInterpreter;
import com.xml.frame.report.interpreter.ExcelTemplateXmlInterpreter;
import com.xml.frame.report.interpreter.FormatterServerXmlInterpreter;
import com.xml.frame.report.interpreter.FormatterXmlInterpreter;
import com.xml.frame.report.interpreter.IFXmlInterpreter;
import com.xml.frame.report.interpreter.ListXmlInterpreter;
import com.xml.frame.report.interpreter.LogXmlInterpreter;
import com.xml.frame.report.interpreter.LoopXmlInterpreter;
import com.xml.frame.report.interpreter.ResponseXmlInterpreter;
import com.xml.frame.report.interpreter.TableXmlInterpreter;
import com.xml.frame.report.interpreter.WordTemplateXmlInterpreter;
import com.xml.frame.report.interpreter.XmlInterpreter;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;


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
			new LogXmlInterpreter(),
			new WordTemplateXmlInterpreter(),
			new TableXmlInterpreter()
	}; 
	
	
	
	public void setConfig(Element config){
		this.config = config;
	}
	
	public String getUrl() {
		return config.getAttribute("url");
	}
	
	@Override
	protected void onRun() throws Exception {		
		XmlElWalker.eachChildren(this.config, elp, new Loop(){
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
