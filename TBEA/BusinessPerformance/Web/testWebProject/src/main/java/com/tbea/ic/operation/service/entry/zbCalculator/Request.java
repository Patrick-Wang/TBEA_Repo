package com.tbea.ic.operation.service.entry.zbCalculator;

import java.util.Calendar;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;

public class Request{
	Integer zbId; Double val; Calendar cal; Company comp; ZBStatus status;
	ZbInjector injector;
	public Request(Integer zbId, Double val, Calendar cal, Company comp,
			ZBStatus status, ZbInjector injector) {
		super();
		this.zbId = zbId;
		this.val = val;
		this.cal = cal;
		this.comp = comp;
		this.status = status;
		this.injector = injector;
	}

	public ZbInjector getInjector() {
		return injector;
	}

	public void setInjector(ZbInjector injector) {
		this.injector = injector;
	}

	public Integer getZbId() {
		return zbId;
	}

	public void setZbId(Integer zbId) {
		this.zbId = zbId;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public Company getComp() {
		return comp;
	}

	public void setComp(Company comp) {
		this.comp = comp;
	}

	public ZBStatus getStatus() {
		return status;
	}

	public void setStatus(ZBStatus status) {
		this.status = status;
	}
	
}