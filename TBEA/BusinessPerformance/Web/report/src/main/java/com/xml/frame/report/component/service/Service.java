package com.xml.frame.report.component.service;

import com.xml.frame.report.interpreter.*;
import org.w3c.dom.Element;

import com.util.tools.xml.Loop;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.manager.ComponentManager;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;

public class Service extends AbstractXmlComponent implements ServiceRunnable {
	
	public Service(AbstractXmlComponent preComponent,Element e, ComponentManager mgr) {
		super(preComponent,e, mgr);
	}
	
	public Service(AbstractXmlComponent preComponent,Element e, ComponentManager mgr, Context context) {
		super(preComponent,e, mgr);
		this.local = context;
	}


	XmlInterpreter[] interpreters = new XmlInterpreter[] {
			new SqlXmlInterpreter(), 
			new ListXmlInterpreter(), 
			new TableXmlInterpreter(),
			new ContextXmlInterpreter(),
			new MergeXmlInterpreter(),
			new CallXmlInterpreter(),
			new CallServiceXmlInterpreter(),
			new IFXmlInterpreter(),
			new LoopXmlInterpreter(),
			new LogXmlInterpreter(),
			new WordTemplateXmlInterpreter(),
			new WordMergeXmlInterpreter()
	};



	protected JpaTransaction getTransactionManager() {
		return (JpaTransaction) getVar(config.getAttribute("transaction"));
	}

	@Override
	protected void onRun() throws Exception {
		JpaTransaction tr = getTransactionManager();
		if (null != tr){
			tr.run(this);
		}else{
			run();		
		}
	}

	@Override
	public void run() throws Exception{
		XmlElWalker.eachChildren(config, elp, new Loop() {
			@Override
			public void on(Element elem) throws Exception {
				for (XmlInterpreter interpreter : interpreters) {	
					if (interpreter.accept(Service.this, elem)) {
						if (ReportLogger.trace().isDebugEnabled()){
							ReportLogger.trace().debug("service : " + XmlUtil.toStringFromDoc(elem));
						}
						break;
					}
				}
			}
		});
	}

	@Override
	public AbstractXmlComponent clone(Element e) {
		Service serv = new Service(preComponent, e, mgr);
		serv.local = this.local;
		serv.global = this.global;
		return serv;
	}
}
