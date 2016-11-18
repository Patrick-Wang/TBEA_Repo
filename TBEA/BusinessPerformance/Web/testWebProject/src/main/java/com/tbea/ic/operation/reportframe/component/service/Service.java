package com.tbea.ic.operation.reportframe.component.service;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.interpreter.CallServiceXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.CallXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ContextXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.IFXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.ListXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.LogXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.LoopXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.MergeXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.SqlXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.TableXmlInterpreter;
import com.tbea.ic.operation.reportframe.interpreter.XmlInterpreter;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

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
			new LogXmlInterpreter()
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
