package com.tbea.ic.operation.service.extendauthority;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ExtendAuthorityService {

	List<Company> getAuthedCompanies(Account account, AuthType yszkdialylookup);
	
	List<Company> getAuthedCompanies(Account account, Integer yszkdialylookup);

	Boolean hasAuthority(Account account, AuthType authType);

	void removeCache(Account account);

	List<Company> getAuthedCompaniesForSbd(Account account, AuthType authType);

	List<Company> getAuthedCompaniesForByqXl(Account account, AuthType authType);

	Boolean hasAuthority(Account account, Integer authType);

}
