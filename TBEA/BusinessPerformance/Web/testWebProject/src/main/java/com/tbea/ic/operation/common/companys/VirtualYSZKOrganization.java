package com.tbea.ic.operation.common.companys;


class VirtualYSZKOrganization extends AbstractOrganization {

	public VirtualYSZKOrganization(Organization BM) {
		append(getCompany(CompanyType.DBSBDCYJT, 1)
	    		.append(clone(BM, CompanyType.SBGS))
	    		.append(clone(BM, CompanyType.LLGS))
		).append(
				getCompany(CompanyType.NFSBDCYJT, 2)
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.DLGS))
		).append(
				getCompany(CompanyType.XBCZT, 3)
				.append(clone(BM, CompanyType.XBC))
				.append(clone(BM, CompanyType.TBGS))
		).append(
				getCompany(CompanyType.BYQCY, 4)
				.append(clone(BM, CompanyType.SBGS))
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.XBC))
		).append(
				getCompany(CompanyType.XLCY, 5)
				.append(clone(BM, CompanyType.LLGS))
				.append(clone(BM, CompanyType.XLC))
				.append(clone(BM, CompanyType.DLGS))
		).append(
				getCompany(CompanyType.SBDCYJT, 6)
				.append(clone(BM, CompanyType.SBGS))
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.XBC))
				.append(clone(BM, CompanyType.TBGS))
				.append(clone(BM, CompanyType.LLGS))
				.append(clone(BM, CompanyType.XLC))
				.append(clone(BM, CompanyType.DLGS))
		);
	}




	@Override
	public int getDepth() {
		return 2;
	}

}
