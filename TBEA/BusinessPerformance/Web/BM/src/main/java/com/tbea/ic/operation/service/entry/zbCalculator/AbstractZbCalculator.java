package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.common.ZBStatus;


abstract class AbstractZbCalculator implements ZbCalculator{
	
	protected ZbInjector injector;
	RequestHandler<Request> handler;
	
	public AbstractZbCalculator(ZbInjector injector, RequestHandler<Request> handler) {
		this.injector = injector;
		this.handler = handler;
	}

	public ZbInjector getInjector(){
		return injector;
	}
	
	public void reset(){
		this.handler.reset();
	}

	abstract protected void onHandling(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status);
	
	public void compute(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status){
		if (!this.handler.handle(new Request(zbId, val, cal, comp, status, injector))){
			onHandling(zbId, val, cal, comp, status);
		}
	}
}
