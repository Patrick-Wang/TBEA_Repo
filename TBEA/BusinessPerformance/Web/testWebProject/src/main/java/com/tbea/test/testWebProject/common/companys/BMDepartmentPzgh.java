package com.tbea.test.testWebProject.common.companys;

import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;

class BMDepartmentPzgh extends AbstractOrganization {

	public BMDepartmentPzgh() {
		    append(getCompany(CompanyType.SB, 1)
						.append(getCompany(CompanyType.BYQC, 101))
						.append(getCompany(CompanyType.ZTFGS, 103))
						.append(getCompany(CompanyType.KJHGQ, 104))
						.append(getCompany(CompanyType.SKGS, 107))
			).append(
					getCompany(CompanyType.HB, 2)
						.append(getCompany(CompanyType.DQFGS, 201))
			).append(
					getCompany(CompanyType.XB, 3)
						.append(getCompany(CompanyType.TB, 301))
						.append(getCompany(CompanyType.ZTGS, 302))
						.append(getCompany(CompanyType.XBGS, 303)));
	}
	
//	@Override
//	public Company getCompany(Integer id) {
//		Company ret = null;
//		if (id >= 100) {
//			int top = id / 100 - 1;
//			if (top < topComps.size()) {
//				int sub = id % 100 - 1;
//				if (topComps.get(top).getSubCompanys().size() > sub) {
//					ret = topComps.get(top).getSubCompanys().get(sub);
//				}
//			}
//		} else if (id >= 1 && topComps.size() >= id) {
//			ret = topComps.get(id - 1);
//		}
//
//		return ret;
//	}

	@Override
	public int getDepth() {
		return 2;
	}

}
