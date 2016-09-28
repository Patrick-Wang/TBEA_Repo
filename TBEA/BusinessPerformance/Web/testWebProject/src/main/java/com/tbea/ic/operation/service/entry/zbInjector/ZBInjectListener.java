package com.tbea.ic.operation.service.entry.zbInjector;

import java.util.Calendar;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface ZBInjectListener {
	public void onInjected(Integer zbId, double val, Calendar cal, Company comp, ZBStatus status, Calendar time);
}
