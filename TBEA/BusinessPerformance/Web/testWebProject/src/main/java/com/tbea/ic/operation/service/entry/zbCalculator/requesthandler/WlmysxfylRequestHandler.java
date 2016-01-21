package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import java.util.Calendar;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class WlmysxfylRequestHandler extends RequestHandler<Request>{
	Double xssr;
	Double sxfy;
	
	void setSxfyl(ZbInjector injector, Calendar cal, Company comp, ZBStatus status){
		if (null != sxfy  && null != xssr && Util.isPositive(xssr) && Util.isPositive(sxfy)){
			injector.inject(GSZB.WLMYSXFYL233.getValue(), sxfy / xssr, cal, comp, status);
		} else{
			injector.remove(GSZB.WLMYSXFYL233.getValue(), cal, comp);
		}
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (GSZB.XSSR_WLMYSR16.getValue().equals(request.getZbId())){
			xssr = request.getVal();
		} else if (GSZB.WLMY230.getValue().equals(request.getZbId())){
			sxfy = request.getVal();
		} else if (GSZB.WLMYSXFYL233.getValue().equals(request.getZbId())){
			setSxfyl(request.getInjector(), request.getCal(), request.getComp(), request.getStatus());
			return true;
		}
		return false;
	}

	@Override
	protected void onReset() {
		xssr = null;
		sxfy = null;
	}
};
