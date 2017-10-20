package com.tbea.erp.report.model.dao.account;

import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.Authority;

import java.util.List;


public interface AccountDao  {

	Account getByName(String usrName);

	List<Authority> getAuthority(Account account);

}
