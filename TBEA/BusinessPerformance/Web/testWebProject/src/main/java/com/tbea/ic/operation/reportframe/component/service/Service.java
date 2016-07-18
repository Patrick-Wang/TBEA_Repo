package com.tbea.ic.operation.reportframe.component.service;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.controller.Controller;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.interpreter.CallServiceXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.CallXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ContextXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ExcelTemplateXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.FormatterServerXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.FormatterXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.IFXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ListXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.MergeXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.SqlXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.TableXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.XmlInterpreter;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class Service extends AbstractXmlComponent implements ServiceRunnable {
	
	public Service(Element e, ComponentManager mgr) {
		super(e, mgr);
	}
	
	public Service(Element e, ComponentManager mgr, Context context) {
		super(e, mgr);
		this.local = context;
	}


	XmlInterpreter[] interpreters = new XmlInterpreter[] {
			new SqlXmlInterpreter(), 
			new ListXmlInterpreter(), 
			new TableXmlInterpreter(),
			new ContextXmlInterpreter(),
			new MergeXmlInterpreter(),
			new CallXmlInterpreter(),
			new ExcelTemplateXmlInterpreter(),
			new FormatterServerXmlInterpreter(),
			new FormatterXmlInterpreter(),
			new CallServiceXmlInterpreter(),
			new IFXmlInterpreter()
	};



	protected Transaction getTransactionManager() {
		return (Transaction) getVar(config.getAttribute("transaction"));
	}

	@Override
	protected void onRun() throws Exception {
		Transaction tr = getTransactionManager();
		if (null != tr){
			tr.run(this);
		}else{
			run();
		}
	}

	@Override
	public void run() throws Exception{
		XmlUtil.each(config.getChildNodes(), new OnLoop() {
			@Override
			public void on(Element elem) throws Exception {
				for (XmlInterpreter interpreter : interpreters) {
					if (interpreter.accept(Service.this, elem)) {
						break;
					}
				}
			}
		});
	}

	@Override
	public AbstractXmlComponent clone(Element e) {
		Service serv = new Service(e, mgr);
		serv.local = this.local;
		serv.global = this.global;
		return serv;
	}
}
