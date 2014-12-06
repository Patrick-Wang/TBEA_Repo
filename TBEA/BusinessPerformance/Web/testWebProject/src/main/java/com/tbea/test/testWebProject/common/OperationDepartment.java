package com.tbea.test.testWebProject.common;

import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;

class OperationDepartment extends AbstractOrganization {

	
	public OperationDepartment() {

		Company jt = getCompany(CompanyType.JT, 9999);
		topComps.add(jt);
		Company sbd = getCompany(CompanyType.SBDCY, 999);
		Company xny = getCompany(CompanyType.XNYCY, 888);
		Company ny = getCompany(CompanyType.NYCY, 777);
		Company gcl = getCompany(CompanyType.GCL, 666);
		jt.append(sbd).append(xny).append(ny)
				.append(gcl)
				.append(getCompany(CompanyType.ZH, 27));
		
		sbd.append(getCompany(CompanyType.SB, 1))
				.append(getCompany(CompanyType.HB, 2))
				.append(getCompany(CompanyType.XB, 3))
				.append(getCompany(CompanyType.TB, 301))
				.append(getCompany(CompanyType.LL, 4))
				.append(getCompany(CompanyType.XL, 5))
				.append(getCompany(CompanyType.DL, 6));

		xny.append(getCompany(CompanyType.XNY, 30))
				.append(getCompany(CompanyType.GY, 29));

		ny.append(getCompany(CompanyType.TCNY, 66))
				.append(getCompany(CompanyType.NDGS, 25))
				.append(getCompany(CompanyType.ZJWL, 74));

		gcl.append(getCompany(CompanyType.JCK, 23))
				.append(getCompany(CompanyType.GCGS, 70));
	}

	@Override
	public int getDepth() {
		return 3;
	}

}
