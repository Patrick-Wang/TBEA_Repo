package com.tbea.ic.operation.common.companys;

import java.util.List;

public interface Organization {
	boolean owns(Company comp);
	Company getCompany(CompanyType type);
	Company getCompany(Integer id);
	Company getCompany(String name);
	List<Company> getTopCompany();
	int getDepth();
	List<Company> getBottomCompany();
}
