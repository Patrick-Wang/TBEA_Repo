package com.tbea.ic.operation.common.companys;

import java.util.ArrayList;
import java.util.List;

public class VirtualJYZBOrganization extends AbstractOrganization {

	public VirtualJYZBOrganization(VirtualYSZKOrganization yszk, Organization BM) {
		append(this.getCompany(CompanyType.GFGS, 0)
			.append(getCompany(CompanyType.SBDCYJT, BM.getCompany(CompanyType.SBDCYJT).getId())
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
					.append(clone(BM, CompanyType.NDGS))
					.append(clone(BM, CompanyType.TCNY))					
			).append(
					clone(BM, CompanyType.ZHGS)					
			).append(this.getCompany(CompanyType.GCCY, 58220)
					.append(clone(BM, CompanyType.JCKGS_JYDW))
					.append(clone(BM, CompanyType.GJGCGS_GFGS))
			));
	}


	@Override
	public int getDepth() {
		return 3;
	}

	public static boolean isSyb(CompanyType compType) {
		return CompanyType.SBDCYJT == compType
				|| CompanyType.XNYSYB == compType
				|| CompanyType.NYSYB == compType
				|| CompanyType.GCCY == compType;
	}

	public static boolean isSbdcy(CompanyType compType){
		return CompanyType.BYQCY == compType ||
				CompanyType.XLCY == compType ||
				CompanyType.DBSBDCYJT == compType ||
				CompanyType.NFSBDCYJT == compType;
	}
	
	public static List<Company> getJydw(CompanyManager companyManager, CompanyType sybOrJydw){
		List<Company> comps = new ArrayList<Company>();
		if (isSyb(sybOrJydw)) {
			if (CompanyType.GCCY == sybOrJydw) {
				Organization orgJyzb = companyManager
						.getVirtualJYZBOrganization();
				Organization org = companyManager.getBMDBOrganization();
				comps = new ArrayList<Company>();
				for (Company comp : orgJyzb.getCompany(sybOrJydw)
						.getSubCompanies()) {
					comps.add(org.getCompany(comp.getType()));
				}
			} else {
				Organization org = companyManager.getBMDBOrganization();
				comps = org.getCompany(sybOrJydw).getSubCompanies();
			}
		} else if (isSbdcy(sybOrJydw)) {
			Organization orgJyzb = companyManager.getVirtualJYZBOrganization();
			comps = orgJyzb.getCompany(sybOrJydw).getSubCompanies();
		} else {
			Organization org = companyManager.getBMDBOrganization();
			comps = new ArrayList<Company>();
			comps.add(org.getCompany(sybOrJydw));
		}
		return comps;
	}
}
