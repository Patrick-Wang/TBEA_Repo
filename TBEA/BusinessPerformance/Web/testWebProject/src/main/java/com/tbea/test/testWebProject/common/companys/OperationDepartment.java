package com.tbea.test.testWebProject.common.companys;

import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;

class OperationDepartment extends AbstractOrganization {

	public OperationDepartment() {
		append(getCompany(CompanyType.JT, 9999)
				.append(getCompany(CompanyType.SBDCY, 999)
						.append(getCompany(CompanyType.SB, 1))
						.append(getCompany(CompanyType.HB, 2))
						.append(getCompany(CompanyType.XB, 3))
						.append(getCompany(CompanyType.TB, 301))
						.append(getCompany(CompanyType.LL, 4))
						.append(getCompany(CompanyType.XL, 5))
						.append(getCompany(CompanyType.DL, 6)))
				.append(getCompany(CompanyType.XNYCY, 888)
						.append(getCompany(CompanyType.XNY, 30))
						.append(getCompany(CompanyType.GY, 29)))
				.append(getCompany(CompanyType.NYCY, 777)
						.append(getCompany(CompanyType.TCNY, 66))
						.append(getCompany(CompanyType.NDGS, 25))
						.append(getCompany(CompanyType.ZJWL, 74)))
				.append(getCompany(CompanyType.GCL, 666)
						.append(getCompany(CompanyType.JCK, 23))
						.append(getCompany(CompanyType.GCGS, 70)))
				.append(getCompany(CompanyType.ZH, 27)));
	}

	@Override
	public int getDepth() {
		return 3;
	}

}
