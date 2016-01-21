package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class RjlrRequestHandler extends RequestHandler<Request>{
	Double lrze;
	Double rs;
	
	void setRjlr(ZbInjector injector, Calendar cal, Company comp, ZBStatus status){
		if (null != lrze  && null != rs && Util.isPositive(rs) && Util.isPositive(lrze)){
			injector.inject(GSZB.RJLR62.getValue(), lrze / rs, cal, comp, status);
		} else{
			injector.remove(GSZB.RJLR62.getValue(), cal, comp);
		}
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (GSZB.RS61.getValue().equals(request.getZbId())){
			rs = request.getVal();
		}else if (GSZB.LRZE1.getValue().equals(request.getZbId())){
			lrze = request.getVal();
		}else if (GSZB.RJLR62.getValue().equals(request.getZbId())){
			setRjlr(request.getInjector(), request.getCal(), request.getComp(), request.getStatus());
			return true;
		}
		return false;
	}


	@Override
	protected void onReset() {
		lrze = null;
		rs = null;
	}
};
