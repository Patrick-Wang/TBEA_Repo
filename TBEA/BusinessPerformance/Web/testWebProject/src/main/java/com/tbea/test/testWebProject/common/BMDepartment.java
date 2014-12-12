package com.tbea.test.testWebProject.common;

import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;

class BMDepartment extends AbstractOrganization {

	public BMDepartment() {
		append(getCompany(CompanyType.XL, 5));
	}

	@Override
	public Company getCompany(Integer id) {
		if (id == 5) {
			return topComps.get(0);
		}
		return null;
	}

	@Override
	public int getDepth() {
		return 2;
	}

}
