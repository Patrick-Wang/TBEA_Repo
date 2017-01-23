package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class RatioRequestHandler extends RequestHandler<Request>{
	Double sub;
	Double base;
	Integer destZb;
	Integer subZb;
	Integer baseZb;
	
	public RatioRequestHandler(GSZB destZb, GSZB subZb, GSZB baseZb) {
		super();
		this.destZb = destZb.value();
		this.subZb = subZb.value();
		this.baseZb = baseZb.value();
	}

	void setRatio(ZbInjector injector, Calendar cal, Company comp, ZBStatus status, Calendar time){
		if (null != sub  && null != base && Util.isPositive(base) && Util.isPositive(sub)){
			injector.inject(destZb, sub / base, cal, comp, status, time);
		} else{
			injector.remove(destZb, cal, comp);
		}
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (subZb.equals(request.getZbId())){
			sub = request.getVal();
		} else if (baseZb.equals(request.getZbId())){
			base = request.getVal();
		} else if (destZb.equals(request.getZbId())){
			setRatio(request.getInjector(), request.getCal(), request.getComp(), request.getStatus(),  request.getTime());
			return true;
		}
		return false;
	}

//	@Override
//	protected void onReset() {
//		sub = null;
//		base = null;
//	}
};
