package com.tbea.ic.operation.controller.webservice.account;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@WebService(endpointInterface = "com.tbea.ic.operation.controller.webservice.account.ServiceAccount",
serviceName = "ServiceAccount")
public class ServiceAccountImpl implements ServiceAccount{

	@Autowired
	LoginService loginService;
	
	@Override
	public ServiceUser login(String userName, String psw) {
		Account account = loginService.Login(userName, psw);
		if (null != account){
			ServiceSession session = ServiceSession.create();
			session.setAttribute("account", account);
			return session.getSessionUser();
		}
		return null;
	}

	@Override
	public void logout(ServiceUser user) {
		ServiceSession session = ServiceSession.getSession(user);
		if(null != session){
			session.invalidate();
		}
	}
}
