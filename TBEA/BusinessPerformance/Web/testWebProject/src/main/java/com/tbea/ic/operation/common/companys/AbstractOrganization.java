package com.tbea.ic.operation.common.companys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public abstract class AbstractOrganization implements Organization {

	protected List<Company> topComps = new ArrayList<Company>();
	protected Map<CompanyManager.CompanyType, Company> typeCompMap = new HashMap<CompanyManager.CompanyType, Company>();
	protected Map<Integer, Company> idCompMap = new HashMap<Integer, Company>();
	
	protected Company clone(Organization org, CompanyManager.CompanyType ty){
		Company otherComp = org.getCompany(ty);
		Company comp = this.getCompany(otherComp.getType(), otherComp.getId());
		List<Company> subs = otherComp.getSubCompanys();
		for (Company sub : subs){
			comp.append(clone(org, sub.getType()));
		}
		return comp;
	}
	
	protected Company getCompany(CompanyManager.CompanyType ty, Integer id) {
		Company comp = ty.getCompany(id);
		typeCompMap.put(ty, comp);
		idCompMap.put(id, comp);
		return comp;
	}

	
	protected AbstractOrganization append(Company comp){
		topComps.add(comp);
		return this;
	}
	
	private Company queryCompany(List<Company> comps, String name) {
		Company ret = null;
		if (null != comps) {
			for (Company sub : comps) {
				if (sub.getName().equals(name)) {
					ret = sub;
				} else {
					ret = queryCompany(sub.getSubCompanys(), name);
				}

				if (null != ret) {
					break;
				}
			}
		}
		return ret;
	}

	
	@Override
	public Company getCompany(String name){
		return queryCompany(this.topComps, name);
	}
	
	@Override
	public Company getCompany(CompanyType type) {
		return typeCompMap.get(type);
	}

	@Override
	public Company getCompany(Integer id) {
		return idCompMap.get(id);//queryCompany(topComps, id);
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
