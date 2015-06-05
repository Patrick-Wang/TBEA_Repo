package com.tbea.ic.operation.controller.webservice.account;

import javax.jws.WebService;

@WebService
public interface ServiceAccount {
	ServiceUser login(String userName, String psw);
	void logout(ServiceUser user);
}
