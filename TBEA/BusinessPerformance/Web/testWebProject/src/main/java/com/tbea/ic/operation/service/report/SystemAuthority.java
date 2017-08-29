package com.tbea.ic.operation.service.report;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

public class SystemAuthority {
	Object auth;
	CompanyManager companyManager;
	Account account;
	ExtendAuthorityService extendAuthService;
	public SystemAuthority(CompanyManager companyManager, Account account, ExtendAuthorityService extendAuthService) {
		super();
		this.companyManager = companyManager;
		this.account = account;
		this.extendAuthService = extendAuthService;
	}
	
	public List<Integer> ids(){
		return extendAuthService.getAuths(account);
	}
	
	public List<Integer> cids(Integer authType){
		List<Company> comps = extendAuthService.getAuthedCompanies(account, authType);
		List<Integer> compIds = new ArrayList<Integer>();
		for (Company comp : comps){
			compIds.add(comp.getId());
		}
		return compIds;
	}
	
	public List<CompanyType> ctypes(Integer authType){
		List<Company> comps = extendAuthService.getAuthedCompanies(account, authType);
		List<CompanyType> compIds = new ArrayList<CompanyType>();
		for (Company comp : comps){
			compIds.add(comp.getType());
		}
		return compIds;
	}
	
	public Boolean has(Integer authType){
		return extendAuthService.hasAuthority(account, authType);
	}
}
