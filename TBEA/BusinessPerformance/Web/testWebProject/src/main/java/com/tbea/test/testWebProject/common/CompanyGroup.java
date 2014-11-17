package com.tbea.test.testWebProject.common;

import com.tbea.test.testWebProject.common.Company.Type;

public class CompanyGroup extends Company {

	private Company.Type[] types = null;
	private Company[] comps = null;
	protected CompanyGroup(String id, String name, Type[] types) {
		super(id, name);
		this.types = types;
		
	}
	
	public Company[] getCompanys(){
		if (comps == null){
			comps = new Company[types.length];
			for (int i = 0; i < comps.length; ++i){
				comps[i] = Company.get(types[i]);
			}
		}
		return comps;
	}
	
	public boolean contains(Company comp){
		for (int i = getCompanys().length - 1; i >= 0; --i){
			if (comps[i] == comp){
				return true;
			}
		}
		return false;
	}
	
}
