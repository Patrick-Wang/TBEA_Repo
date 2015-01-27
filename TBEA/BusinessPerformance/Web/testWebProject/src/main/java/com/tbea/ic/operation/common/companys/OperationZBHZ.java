package com.tbea.ic.operation.common.companys;



import java.util.List;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public class OperationZBHZ extends AbstractOrganization {

	@Override
	public int getDepth() {
		return 2;
	}

	public OperationZBHZ(OperationDepartment org) {
		super();
		append(getCompany(CompanyType.JT, org.getCompany(CompanyType.JT).getId()));
		topComps.addAll(org.getCompany(CompanyType.JT).getSubCompanysWithLeaves());
		List<Company> coms = org.getBottomCompany();
		for (int i = 0; i < coms.size(); ++i){
			getCompany(CompanyType.JT).append(coms.get(i));
		}
	}

}
