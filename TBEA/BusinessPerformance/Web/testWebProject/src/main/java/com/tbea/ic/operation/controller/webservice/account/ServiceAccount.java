package com.tbea.ic.operation.controller.webservice.account;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ServiceAccount {
	ServiceUser login(
			@WebParam(name = "userName")
			String userName, 
			@WebParam(name = "psw")
			String psw);
	void logout(
			@WebParam(name = "usr")
			ServiceUser user);
}
