package com.tbea.erp.report.model.dao.account;

import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.Authority;

import java.util.List;


public interface AccountDao  {

	Account getAccount(String usrName, String roleName);

	List<Authority> getAuthority(Account account);

}
