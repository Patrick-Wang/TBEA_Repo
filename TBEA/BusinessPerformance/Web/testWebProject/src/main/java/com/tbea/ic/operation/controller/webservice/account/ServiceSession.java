package com.tbea.ic.operation.controller.webservice.account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServiceSession {
	private static Map<String, ServiceSession> onlineSessions = Collections.synchronizedMap(new HashMap<String, ServiceSession>());
	private Map<String, Object> attrs = Collections.synchronizedMap(new HashMap<String, Object>());
	
	private ServiceUser serviceUser;
	
	private ServiceSession(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}

	public static ServiceSession create(){
		ServiceUser usr = new ServiceUser(UUID.randomUUID().toString());
		ServiceSession session = new ServiceSession(usr);
		onlineSessions.put(usr.getUser(), session);
		return session;
	}
	
	public void invalidate(){
		onlineSessions.remove(this.serviceUser.getUser());
	}
	
	public static ServiceSession getSession(ServiceUser usr){
		return onlineSessions.get(usr.getUser());
	}
	
	public void setAttribute(String name, Object obj){
		this.attrs.put(name, obj);
	}
	
	public Object getAttribute(String name){
		return this.attrs.get(name);
	}	
	
	public ServiceUser getSessionUser(){
		return serviceUser;
	}
}
