package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;
import com.tbea.ic.operation.common.ZBStatus;

public class GeneralZbCalculator extends AbstractZbCalculator{
	
	public GeneralZbCalculator(ZbInjector injector) {
		super(injector);
	}

	@Override
	protected void onHandling(Integer zbId, Double val, Calendar cal,
			Company comp, ZBStatus status) {
		if (null != val){
			injector.inject(zbId, val, cal, comp, status);
		}
	}
	
}
