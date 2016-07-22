package com.tbea.ic.operation.common.companys;


class VirtualGBOrganization extends AbstractOrganization {

	public VirtualGBOrganization(Organization BM) {
		append(
				getCompany(CompanyType.BYQCY, 1)
				.append(clone(BM, CompanyType.SBGS))
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.XBC))
		).append(
				getCompany(CompanyType.XLCY, 2)
				.append(clone(BM, CompanyType.LLGS))
				.append(clone(BM, CompanyType.XLC))
				.append(clone(BM, CompanyType.DLGS))
		).append(
				getCompany(CompanyType.SBDCYJT, 3)
				.append(clone(BM, CompanyType.SBGS))
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.XBC))
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
