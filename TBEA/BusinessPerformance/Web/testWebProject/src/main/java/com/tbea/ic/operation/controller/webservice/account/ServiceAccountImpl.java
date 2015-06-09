package com.tbea.ic.operation.controller.webservice.account;

import java.security.Principal;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sun.net.httpserver.HttpExchange;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@WebService(endpointInterface = "com.tbea.ic.operation.controller.webservice.account.ServiceAccount",
serviceName = "ServiceAccount")
public class ServiceAccountImpl implements ServiceAccount{

	@Autowired
	LoginService loginService;
	
	@Resource
	private WebServiceContext wsc;
	
	@Override
	public ServiceUser login(String userName, String psw) {
		Account account = loginService.Login(userName, psw);
		if (null != account){
			HttpExchange he = (HttpExchange) wsc.getMessageContext().get("com.sun.xml.internal.ws.http.exchange");
			he.setAttribute("account", account);
			//wsc.getMessageContext().put("account", account);
			return null;
		}
		return null;
	}

	@Override
	public void logout(ServiceUser user) {
	
		MessageContext mc = wsc.getMessageContext();
		HttpExchange he = (HttpExchange) wsc.getMessageContext().get("com.sun.xml.internal.ws.http.exchange");
		Account account = (Account) he.getAttribute("account");
		//Principal pri = wsc.getUserPrincipal();
		//String name = pri.getName();
		ServiceSession session = ServiceSession.getSession(user);
		if(null != session){
			session.invalidate();
		}
	}
}
