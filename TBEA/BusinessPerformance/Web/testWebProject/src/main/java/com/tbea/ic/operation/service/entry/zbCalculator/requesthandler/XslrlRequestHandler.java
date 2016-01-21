package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class XslrlRequestHandler extends RequestHandler<Request>{
	Double xssr;
	Double lrze;
	
	void setXslrl(ZbInjector injector, Calendar cal, Company comp, ZBStatus status){
		if (null != lrze  && null != xssr && Util.isPositive(xssr) && Util.isPositive(lrze)){
			injector.inject(GSZB.XSLRL_28.getValue(), lrze / xssr, cal, comp, status);
		}else{
			injector.remove(GSZB.XSLRL_28.getValue(), cal, comp);
		}
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (GSZB.XSSR6.getValue().equals(request.getZbId())){
			xssr = request.getVal();
		} else if (GSZB.LRZE1.getValue().equals(request.getZbId())){
			lrze = request.getVal();
		} else if (GSZB.XSLRL_28.getValue().equals(request.getZbId())){
			setXslrl(request.getInjector(), request.getCal(), request.getComp(), request.getStatus());
			return true;
		}
		return false;
	}
	
	@Override
	protected void onReset() {
		xssr = null;
		lrze = null;
	}
};
