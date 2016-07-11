package com.tbea.ic.operation.reportframe.component.service;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.interpreter.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.interpreter.CallXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ContextXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ListXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.MergeXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.SqlXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.TableXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.XmlInterpreter;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class Service extends AbstractXmlComponent implements Runnable {
	
	public Service(Element e, ComponentManager mgr) {
		super(e, mgr);
	}

	XmlInterpreter[] interpreters = new XmlInterpreter[] {
			new SqlXmlInterpreter(), 
			new ListXmlInterpreter(), 
			new TableXmlInterpreter(),
			new ContextXmlInterpreter(),
			new MergeXmlInterpreter(),
			new CallXmlInterpreter()
	};



	protected Transaction getTransactionManager() {
		return (Transaction) getVar(config.getAttribute("transaction"));
	}

	@Override
	protected void onRun() {
		Transaction tr = getTransactionManager();
		if (null != tr){
			tr.run(this);
		}else{
			run();
		}
	}

	@Override
	public void run() {
		XmlUtil.each(config.getChildNodes(), new OnLoop(){
			@Override
			public void on(Element elem) {
				for (XmlInterpreter interpreter : interpreters) {
					if (interpreter.accept(Service.this, elem)) {
						break;
					}
				}
			}
		});
	}
}
