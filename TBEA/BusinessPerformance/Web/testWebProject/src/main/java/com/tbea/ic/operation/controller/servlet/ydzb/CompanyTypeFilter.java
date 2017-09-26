package com.tbea.ic.operation.controller.servlet.ydzb;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;

public class CompanyTypeFilter implements CompanySelection.Filter{
	
	private List<Company> companies;
	Organization org;
	Company dbsbd;
	Company nfsbd;

//	Company xjtc;
	public CompanyTypeFilter(List<Company> companies, Organization org){
		this.org = org;
		this.companies = companies;
		dbsbd = org.getCompanyByType(CompanyType.DBSBDCYJT);
		nfsbd = org.getCompanyByType(CompanyType.NFSBDCYJT);

		updateCompanies();
	}
		
	private void addCategory(List<Company> comps, Company category){
		int count = 0;
		for (Company comp : category.getSubCompanies()){
			if(keep(org.getCompanyByType(comp.getType()))){
				++count;
			}
		}
		if (category.getSubCompanies().size() == count){
			comps.add(category);
		}
	}
	
	private void updateCompanies(){
		List<Company> ret = new ArrayList<Company>();
		for (int i = 0; i < this.companies.size(); ++i){
			ret.add(org.getCompanyByType(this.companies.get(i).getType()));
			companies.set(i, ret.get(i));
		}
		addCategory(ret, dbsbd);
		addCategory(ret, nfsbd);

		//addCategory(ret, xjtc);		
		this.companies = ret;
	}
	
	private boolean contains(Company comp){
		for (Company tmpComp : companies){
			if (null != tmpComp && tmpComp.contains(comp)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean keep(Company comp) {
		return 
			!dbsbd.contains(comp) && 
			!nfsbd.contains(comp) && 
			(companies.contains(comp) || contains(comp));
	}
	
	@Override
	public boolean keepGroup(Company comp) {
		for (Company tmpComp : companies){
			if (comp.contains(tmpComp) ||
				comp == tmpComp ||
				tmpComp.contains(comp)){
				return true;
			}
		}
		return false;
	}

}