package com.tbea.ic.operation.service.cwcpdlml;

import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;

public interface CwcpdlmlService {

	void importFromNC(Date d, Company comp);

}
