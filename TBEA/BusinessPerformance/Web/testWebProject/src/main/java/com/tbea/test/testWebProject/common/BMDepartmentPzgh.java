package com.tbea.test.testWebProject.common;

import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;

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

	@Override
	public Company getCompany(Integer id) {
		int top = id / 100;
		if (top <= topComps.size()) {
			int sub = id % 10;
			if (sub % 100 / 10 == 0
					&& topComps.get(top - 1).getSubCompanys().size() >= sub) {
				return topComps.get(top - 1).getSubCompanys().get(sub);
			}
		}
		return null;
	}

	@Override
	public int getDepth() {
		return 2;
	}

}
