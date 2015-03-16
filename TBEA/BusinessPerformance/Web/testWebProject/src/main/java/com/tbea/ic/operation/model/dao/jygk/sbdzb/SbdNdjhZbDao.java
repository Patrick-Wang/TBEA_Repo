package com.tbea.ic.operation.model.dao.jygk.sbdzb;

import com.tbea.ic.operation.common.companys.Company;

public interface SbdNdjhZbDao {
	public Double getYszb(int year, Company comp);
	
	public Double getChzb(int year, Company comp);
}
