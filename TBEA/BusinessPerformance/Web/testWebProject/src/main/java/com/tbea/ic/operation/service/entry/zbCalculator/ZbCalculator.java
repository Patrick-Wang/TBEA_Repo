package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.ZBStatus;

public interface ZbCalculator{
	public void reset();
	public void compute(Integer zbId, Double val, Calendar cal, Company comp, ZBStatus status);
}
