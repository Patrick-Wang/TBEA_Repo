package com.tbea.ic.operation.common.companys;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

class VirtualYSZKOrganization extends AbstractOrganization {

	public VirtualYSZKOrganization(Organization BM) {
		append(getCompany(CompanyType.DBSBDCYJT, 1)
	    		.append(BM.getCompany(CompanyType.SB))
	    		.append(BM.getCompany(CompanyType.LL))
		).append(
				getCompany(CompanyType.NFSBDCYJT, 2)
				.append(BM.getCompany(CompanyType.HB))
				.append(BM.getCompany(CompanyType.DL))
		).append(
				getCompany(CompanyType.XBCZT, 3)
				.append(BM.getCompany(CompanyType.XB))
				.append(BM.getCompany(CompanyType.TBGS))
		).append(
				getCompany(CompanyType.BYQCY, 4)
				.append(BM.getCompany(CompanyType.SB))
				.append(BM.getCompany(CompanyType.HB))
				.append(BM.getCompany(CompanyType.XB))
				.append(BM.getCompany(CompanyType.TBGS))
		).append(
				getCompany(CompanyType.XLCY, 5)
				.append(BM.getCompany(CompanyType.LL))
				.append(BM.getCompany(CompanyType.XL))
				.append(BM.getCompany(CompanyType.DL))
		).append(
				getCompany(CompanyType.SBDCYJT, 6)
				.append(BM.getCompany(CompanyType.SB))
				.append(BM.getCompany(CompanyType.HB))
				.append(BM.getCompany(CompanyType.XB))
				.append(BM.getCompany(CompanyType.TBGS))
				.append(BM.getCompany(CompanyType.LL))
				.append(BM.getCompany(CompanyType.XL))
				.append(BM.getCompany(CompanyType.DL))
		);
	}




	@Override
	public int getDepth() {
		return 2;
	}

}
