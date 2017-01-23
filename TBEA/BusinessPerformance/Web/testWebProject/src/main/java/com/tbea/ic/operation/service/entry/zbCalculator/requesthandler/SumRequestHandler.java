package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;

public class SumRequestHandler extends RequestHandler<Request>{

	Double destVal;
	Integer destZb;
	GSZB[] srcs;
	
	public SumRequestHandler(GSZB destZb, GSZB[] srcs) {
		super();
		this.destZb = destZb.value();
		this.srcs = srcs;
	}


	
	private boolean srcContains(Integer zbs){
		for (GSZB zb : srcs){
			if (zb.value().equals(zbs)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (srcContains(request.getZbId())){
			destVal = MathUtil.sum(destVal, request.getVal());
		} else if (destZb.equals(request.getZbId())){
			request.getInjector().inject(destZb, destVal, request.getCal(), request.getComp(), request.getStatus(),  request.getTime());
			return true;
		}
		return false;
	}

//	@Override
//	protected void onReset() {
//		destVal = null;
//	}
};
