package com.tbea.ic.operation.common.companys;


class BMDepartmentPzgh extends AbstractOrganization {

	public BMDepartmentPzgh() {
		append(
				getCompany(CompanyType.SBGS, 1)
						.append(getCompany(CompanyType.BYQC, 101))
						.append(getCompany(CompanyType.ZTFGS, 103))
						.append(getCompany(CompanyType.KJHGQ, 104))
						.append(getCompany(CompanyType.SKGS, 107)))
				.append(getCompany(CompanyType.HBGS, 2).append(
						getCompany(CompanyType.DQFGS, 201)))
				.append(getCompany(CompanyType.XBC, 3)
						.append(getCompany(CompanyType.TBGS, 301))
						.append(getCompany(CompanyType.BBXSGS, 307))
						.append(getCompany(CompanyType.ZTGS, 302))
						.append(getCompany(CompanyType.XBGS, 303)))
				.append(getCompany(CompanyType.LLGS, 4))
				.append(getCompany(CompanyType.XLC, 5))
				.append(getCompany(CompanyType.DLGS, 6));
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
