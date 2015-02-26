package com.tbea.ic.operation.common.companys;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

class OperationDepartment extends AbstractOrganization {

	public OperationDepartment() {
		append(getCompany(CompanyType.JT, 9999)
				.append(getCompany(CompanyType.SBDCY, 999)
						.append(getCompany(CompanyType.SBGS, 1))
						.append(getCompany(CompanyType.HBGS, 2))
						.append(getCompany(CompanyType.XBC, 3))
						.append(getCompany(CompanyType.TBGS, 301))
						.append(getCompany(CompanyType.LLGS, 4))
						.append(getCompany(CompanyType.XLC, 5))
						.append(getCompany(CompanyType.DLGS, 6)))
				.append(getCompany(CompanyType.XNYCY, 888)
						.append(getCompany(CompanyType.XTNYGS, 30))
						.append(getCompany(CompanyType.XNY, 29)))
				.append(getCompany(CompanyType.NYCY, 777)
						.append(getCompany(CompanyType.TCNY, 66))
						.append(getCompany(CompanyType.NDGS, 25))
						.append(getCompany(CompanyType.ZJWL, 74)))
				.append(getCompany(CompanyType.GCL, 666)
						.append(getCompany(CompanyType.JCK, 23))
						.append(getCompany(CompanyType.GCGS_GCL, 70)))
				.append(getCompany(CompanyType.ZHGS, 27)));
	}

	@Override
	public int getDepth() {
		return 3;
	}

}
