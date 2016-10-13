package com.tbea.ic.operation.service.wgcpqk;

import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;



public interface WgcpqkService {
	void importByqFromNC(Date d, Company comps);

	void importXlFromNC(Date d, Company comps);


}
