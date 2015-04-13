package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class GeneralZbCalculator extends AbstractZbCalculator{
	
	public GeneralZbCalculator(ZbInjector injector) {
		super(injector);
	}

	@Override
	protected void onHandling(Integer zbId, Double val, Calendar cal,
			Company comp) {
		injector.inject(zbId, val, cal, comp);
	}
	
}
