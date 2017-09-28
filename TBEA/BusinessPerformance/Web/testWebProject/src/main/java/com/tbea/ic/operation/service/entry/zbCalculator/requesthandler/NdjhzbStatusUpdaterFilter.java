package com.tbea.ic.operation.service.entry.zbCalculator.requesthandler;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.util.tools.RequestHandler;

public class NdjhzbStatusUpdaterFilter extends RequestHandler<Request>{
	Integer destZb;
	CompanyType comp;
	boolean updated = false;
	public NdjhzbStatusUpdaterFilter(GSZB destZb, CompanyType comp) {
		super();
		this.destZb = destZb.value();
		this.comp = comp;
	}
	
	@Override
	protected boolean onHandle(Request request) {
		if (!updated && this.comp == request.getComp().getType()){
			updated = true;
			Double njh = request.getInjector().remove(destZb, request.getCal(), request.getComp());
			if (njh != null){
				request.getInjector().inject(this.destZb, njh, request.getCal(), request.getComp(), request.getStatus(), request.getTime());
			}
		}
		return false;
	}

//	@Override
//	protected void onReset() {
//		updated = false;
//	}
};
