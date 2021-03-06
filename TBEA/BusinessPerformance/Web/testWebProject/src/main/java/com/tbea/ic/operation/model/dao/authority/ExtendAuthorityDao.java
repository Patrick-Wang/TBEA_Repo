package com.tbea.ic.operation.model.dao.authority;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ExtendAuthorityDao  {

	List<ExtendAuthority> getAuthority(Account account, Company comp);
			
	List<ExtendAuthority> getAuthority(Account account, int authType);

	int getAuthorityCount(Account account, int authType);

	List<Integer> getAuthority(Account account);


}
