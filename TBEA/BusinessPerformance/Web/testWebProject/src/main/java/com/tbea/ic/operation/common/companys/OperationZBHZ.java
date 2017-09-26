package com.tbea.ic.operation.common.companys;



import java.util.List;

public class OperationZBHZ extends AbstractOrganization {

	@Override
	public int getDepth() {
		return 2;
	}

	public OperationZBHZ(OperationDepartment org) {
		super();
		append(getCompany(CompanyType.JT, org.getCompanyByType(CompanyType.JT).getId()));
		topComps.addAll(org.getCompanyByType(CompanyType.JT).getSubCompanysWithLeaves());
		List<Company> coms = org.getBottomCompany();
		for (int i = 0; i < coms.size(); ++i){
			getCompanyByType(CompanyType.JT).append(coms.get(i));
		}
	}

}
