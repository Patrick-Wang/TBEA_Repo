package com.tbea.ic.operation.common.companys;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private CompanyType type;
	private Integer id;
	private Company parentCompany;
	private List<Company> subCompanies = new ArrayList<Company>();
	
	
	public Company(Integer id, CompanyType type) {
		this.type = type;
		this.id = id;
	}

	public Integer level(){
		Integer level = 0;
		Company p = parentCompany;
		while (p != null){
			p = p.getParentCompany();
			++level;
		}
		return level;
	}

	public Integer getId() {
		return id;
	}


	public Company getParentCompany() {
		return parentCompany;
	}


	public List<Company> getSubCompanies() {
		return subCompanies;
	}

	public List<Company> getSubCompanysWithoutLeaves() {
		List<Company> comps = new ArrayList<Company>();
		for (Company comp : subCompanies){
			if (comp.getSubCompanies().isEmpty()){
				comps.add(comp);
			}
		}
		return comps;
	}
	
	public List<Company> getSubCompanysWithLeaves() {
		List<Company> comps = new ArrayList<Company>();
		for (Company comp : subCompanies){
			if (!comp.getSubCompanies().isEmpty()){
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

	public boolean contains(Company comp){
		Company p = comp.getParentCompany();
		while (null != p && p != this){
			p = p.getParentCompany();
		}
		return null != p;
	}
	
	/**
	 * @param subCompanys the subCompanys to set
	 */
	public Company append(Company subCompany) {
		subCompanies.add(subCompany);
		subCompany.setParentCompany(this);
		return this;
	}

	public CompanyType getType(){
		return this.type;
	}
	
	public String getName() {
		return type.getValue();
	}
	
	public List<Company> getLeaves(){
		List<Company> bottomComps = new ArrayList<Company>();
		if (subCompanies.isEmpty()){
			bottomComps.add(this);
		}
		else{
			for (Company sub : subCompanies){
				bottomComps.addAll(sub.getLeaves());
			}
		}
		return bottomComps;
	}
}
