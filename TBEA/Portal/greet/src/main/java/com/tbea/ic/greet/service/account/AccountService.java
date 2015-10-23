package com.tbea.ic.greet.service.account;

import com.tbea.ic.greet.model.entity.Account;

public interface AccountService {

	Account validate(String name, String psw);

	boolean bindSystem(Account account, String sysId, String name, String psw);

	String getLoginUrl(Account account, String sysId);

}
