package com.tbea.ic.operation.controller.servlet.Server;


import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

public class ServiceAuthenticator extends Authenticator {
	
	@Override
	public Result authenticate(HttpExchange arg0) {

		HttpPrincipal p = new HttpPrincipal("name1", "name2");
		String s1 = p.getName();
		String s2 = p.getRealm();
		String s3 = p.getUsername();
		return new Authenticator.Success(p);
	}

}
