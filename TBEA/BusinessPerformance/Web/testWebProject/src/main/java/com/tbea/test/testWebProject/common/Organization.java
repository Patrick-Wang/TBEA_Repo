package com.tbea.test.testWebProject.common;

import java.util.List;

public interface Organization {
	Company getCompany(CompanyManager.CompanyType type);
	Company getCompany(Integer id);
	List<Company> getTopCompany();
	int getDepth();
	List<Company> getBottomCompany();
}
