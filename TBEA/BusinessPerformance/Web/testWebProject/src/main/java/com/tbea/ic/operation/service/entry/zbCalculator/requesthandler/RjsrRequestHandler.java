package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class RjsrRequestHandler extends RequestHandler<Request>{
	Double xssr;
	Double rs;
	
	protected void setRjsr(ZbInjector injector, Calendar cal, Company comp, ZBStatus status){
		if (null != xssr  && null != rs && Util.isPositive(rs) && Util.isPositive(xssr)){
			injector.inject(GSZB.RJSR63.getValue(), xssr / rs, cal, comp, status);
		} else{
			injector.remove(GSZB.RJSR63.getValue(), cal, comp);
		}
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (GSZB.RS61.getValue().equals(request.getZbId())){
			rs = request.getVal();
		}else if (GSZB.XSSR6.getValue().equals(request.getZbId())){
			xssr = request.getVal();
		}else if (GSZB.RJSR63.getValue().equals(request.getZbId())){
			setRjsr(request.getInjector(), request.getCal(), request.getComp(), request.getStatus());
			return true;
		}
		return false;
	}
	
	@Override
	protected void onReset() {
		xssr = null;
		rs = null;
	}
};
