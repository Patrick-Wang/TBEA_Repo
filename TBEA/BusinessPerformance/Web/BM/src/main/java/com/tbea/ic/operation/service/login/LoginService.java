package com.tbea.ic.operation.service.login;

import com.tbea.ic.operation.model.entity.jygk.Account;

public interface LoginService {
	Account Login(String usrName, String psw);
	
	Account SSOLogin(String usrName);

	boolean hasCorpAuth(Account account);

	boolean hasSbdAuth(Account account);
}
