package com.tbea.test.testWebProject.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;

public abstract class AbstractOrganization implements Organization {

	protected List<Company> topComps = new ArrayList<Company>();
	protected Map<CompanyManager.CompanyType, Company> typeIdMap = new HashMap<CompanyManager.CompanyType, Company>();

	protected Company getCompany(CompanyManager.CompanyType ty, Integer id) {
		Company comp = ty.getCompany(id);
		typeIdMap.put(ty, comp);
		return comp;
	}

	private Company queryCompany(List<Company> comps, Integer id) {
		Company ret = null;
		if (null != comps) {
			for (Company sub : comps) {
				if (sub.getId() == id) {
					ret = sub;
				} else {
					ret = queryCompany(sub.getSubCompanys(), id);
				}

				if (null != ret) {
					break;
				}
			}
		}
		return ret;
	}

	@Override
	public Company getCompany(CompanyType type) {
		return typeIdMap.get(type);
	}

	@Override
	public Company getCompany(Integer id) {
		return queryCompany(topComps, id);
	}

	@Override
	public List<Company> getTopCompany() {
		return topComps;
	}
	

	@Override
	public List<Company> getBottomCompany() {
		List<Company> bottomComps = new ArrayList<Company>();
		for (Company sub : topComps){
			bottomComps.addAll(sub.getLeaves());
		}
		return bottomComps;
	}

}
