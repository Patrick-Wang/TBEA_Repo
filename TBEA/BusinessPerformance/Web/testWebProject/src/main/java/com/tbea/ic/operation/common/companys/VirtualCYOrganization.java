package com.tbea.ic.operation.common.companys;


class VirtualCYOrganization extends AbstractOrganization {

	public VirtualCYOrganization(Organization BM) {
		append(
				getCompany(CompanyType.BYQCY, 50000)
				.append(clone(BM, CompanyType.SBGS))
				.append(clone(BM, CompanyType.HBGS))
				.append(clone(BM, CompanyType.XBC))
		).append(
				getCompany(CompanyType.XLCY, 50001)
				.append(clone(BM, CompanyType.LLGS))
				.append(clone(BM, CompanyType.XLC))
				.append(clone(BM, CompanyType.DLGS))
		).append(
				getCompany(CompanyType.PDCY, 50002)
				.append(clone(BM, CompanyType.SBZTFGS))
				//.append(clone(BM, CompanyType.HBDQFGS))
				.append(clone(BM, CompanyType.XBZTGS))
				.append(clone(BM, CompanyType.XBXBGS))
				.append(clone(BM, CompanyType.TBGS))
		).append(
				clone(BM, CompanyType.XKGS)
		);
	}




	@Override
	public int getDepth() {
		return 2;
	}

}
