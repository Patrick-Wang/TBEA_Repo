package com.tbea.ic.operation.service.extendauthority;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ExtendAuthorityService {

	List<Company> getAuthedCompanies(Account account, AuthType yszkdialylookup);

	Boolean hasAuthority(Account account, AuthType authType);

	void removeCache(Account account);

}
