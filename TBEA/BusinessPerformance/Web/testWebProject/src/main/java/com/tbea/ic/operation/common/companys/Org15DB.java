package com.tbea.ic.operation.common.companys;


class Org15DB extends AbstractOrganization {

	public Org15DB() {
		append(getCompany(CompanyType.SBGS, 5))
		.append(getCompany(CompanyType.HBGS, 6))
		.append(getCompany(CompanyType.XBC, 7))
		.append(getCompany(CompanyType.TBGS, 8))
		.append(getCompany(CompanyType.LLGS, 9))
		.append(getCompany(CompanyType.XLC, 10))
		.append(getCompany(CompanyType.DLGS, 11));
	}

	@Override
	public int getDepth() {
		return 1;
	}

}
