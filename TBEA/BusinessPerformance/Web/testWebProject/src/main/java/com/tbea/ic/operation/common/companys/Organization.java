package com.tbea.ic.operation.common.companys;

import java.util.List;

public interface Organization {
	boolean owns(Company comp);
	Company getCompanyByType(CompanyType type);
	Company getCompanyById(Integer id);
	Company getCompanyByName(String name);
	List<Company> getTopCompany();
	int getDepth();
	List<Company> getBottomCompany();
}
