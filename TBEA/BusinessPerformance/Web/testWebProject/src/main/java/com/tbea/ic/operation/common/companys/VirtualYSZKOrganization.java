package com.tbea.ic.operation.common.companys;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

class VirtualYSZKOrganization extends AbstractOrganization {

	public VirtualYSZKOrganization(Organization BM) {
		append(getCompany(CompanyType.DBSBDCYJT, 1)
	    		.append(BM.getCompany(CompanyType.SBGS))
	    		.append(BM.getCompany(CompanyType.LLGS))
		).append(
				getCompany(CompanyType.NFSBDCYJT, 2)
				.append(BM.getCompany(CompanyType.HBGS))
				.append(BM.getCompany(CompanyType.DLGS))
		).append(
				getCompany(CompanyType.XBCZT, 3)
				.append(BM.getCompany(CompanyType.XBC))
				.append(BM.getCompany(CompanyType.TBGS))
		).append(
				getCompany(CompanyType.BYQCY, 4)
				.append(BM.getCompany(CompanyType.SBGS))
				.append(BM.getCompany(CompanyType.HBGS))
				.append(BM.getCompany(CompanyType.XBC))
				.append(BM.getCompany(CompanyType.TBGS))
		).append(
				getCompany(CompanyType.XLCY, 5)
				.append(BM.getCompany(CompanyType.LLGS))
				.append(BM.getCompany(CompanyType.XLC))
				.append(BM.getCompany(CompanyType.DLGS))
		).append(
				getCompany(CompanyType.SBDCYJT, 6)
				.append(BM.getCompany(CompanyType.SBGS))
				.append(BM.getCompany(CompanyType.HBGS))
				.append(BM.getCompany(CompanyType.XBC))
				.append(BM.getCompany(CompanyType.TBGS))
				.append(BM.getCompany(CompanyType.LLGS))
				.append(BM.getCompany(CompanyType.XLC))
				.append(BM.getCompany(CompanyType.DLGS))
		);
	}




	@Override
	public int getDepth() {
		return 2;
	}

}
