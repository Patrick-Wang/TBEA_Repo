package com.tbea.test.testWebProject.common.companys;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private CompanyManager.CompanyType type;
	private Integer id;
	private Company parentCompany;
	private List<Company> subCompanys = new ArrayList<Company>();;
	
	
	public Company(Integer id, CompanyManager.CompanyType type) {
		this.type = type;
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public Company getParentCompany() {
		return parentCompany;
	}


	public List<Company> getSubCompanys() {
		return subCompanys;
	}

	public List<Company> getSubCompanysWithoutLeaves() {
		List<Company> comps = new ArrayList<Company>();
		for (Company comp : subCompanys){
			if (comp.getSubCompanys().isEmpty()){
				comps.add(comp);
			}
		}
		return comps;
	}
	
	public List<Company> getSubCompanysWithLeaves() {
		List<Company> comps = new ArrayList<Company>();
		for (Company comp : subCompanys){
			if (!comp.getSubCompanys().isEmpty()){
				comps.add(comp);
			}
		}
		return comps;
	}
	
	/**
	 * @param parentCompany the parentCompany to set
	 */
	public void setParentCompany(Company parentCompany) {
		this.parentCompany = parentCompany;
	}


	/**
	 * @param subCompanys the subCompanys to set
	 */
	public Company append(Company subCompany) {
		subCompanys.add(subCompany);
		subCompany.setParentCompany(this);
		return this;
	}

	public CompanyManager.CompanyType getType(){
		return this.type;
	}
	
	public String getName() {
		return type.getValue();
	}
	
	public List<Company> getLeaves(){
		List<Company> bottomComps = new ArrayList<Company>();
		if (subCompanys.isEmpty()){
			bottomComps.add(this);
		}
		else{
			for (Company sub : subCompanys){
				bottomComps.addAll(sub.getLeaves());
			}
		}
		return bottomComps;
	}
}
