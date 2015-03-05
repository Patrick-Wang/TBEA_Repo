package com.tbea.ic.operation.common.companys;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

class VirtualJYZBOrganization extends AbstractOrganization {

	public VirtualJYZBOrganization(VirtualYSZKOrganization yszk, Organization BM) {
		append(getCompany(CompanyType.SBDCYJT, BM.getCompany(CompanyType.SBDCYJT).getId())
	    		.append(clone(yszk, CompanyType.BYQCY))
	    		.append(clone(yszk, CompanyType.XLCY))
	    		.append(clone(yszk, CompanyType.DBSBDCYJT))
	    		.append(clone(yszk, CompanyType.NFSBDCYJT))
	    		.append(clone(BM, CompanyType.SBGS))
	    		.append(clone(BM, CompanyType.HBGS))
	    		.append(clone(BM, CompanyType.XBC))
	    		.append(clone(BM, CompanyType.DLGS))
	    		.append(clone(BM, CompanyType.XLC))
	    		.append(clone(BM, CompanyType.LLGS))
		).append(
				clone(BM, CompanyType.XNYSYB)
		).append(
				getCompany(CompanyType.NYSYB, clone(BM, CompanyType.NYSYB).getId())
				.append(getCompany(CompanyType.TCNY_and_XJNY, 1)
						.append(clone(BM, CompanyType.TCNY))
						.append(clone(BM, CompanyType.XJNY)))
				.append(clone(BM, CompanyType.NDGS))
				.append(clone(BM, CompanyType.TCNY))
				.append(clone(BM, CompanyType.XJNY))
				
		).append(
				clone(BM, CompanyType.ZHGS)				
		).append(
				clone(BM, CompanyType.JCKGS_JYDW)	
		).append(
				clone(BM, CompanyType.GJGCGS_GFGS)	
		);
	}


	@Override
	public int getDepth() {
		return 3;
	}

}
