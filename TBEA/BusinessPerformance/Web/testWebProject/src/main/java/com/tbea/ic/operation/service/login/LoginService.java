package com.tbea.ic.operation.service.login;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.model.entity.jygk.Account;

public interface LoginService {
	Account getAppAccount(String appId);
	
	Account Login(String usrName, String psw);
	
	Account SSOLogin(String usrName);

	boolean hasCorpAuth(Account account);

	boolean hasSbdAuth(Account account);

	void logout(Account account, long creationTime, long lastAccessedTime, String ip, JSONArray reqs);
}
