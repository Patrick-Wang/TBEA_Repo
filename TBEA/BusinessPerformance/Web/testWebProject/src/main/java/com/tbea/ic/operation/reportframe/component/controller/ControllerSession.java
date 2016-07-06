package com.tbea.ic.operation.reportframe.component.controller;

import javax.servlet.http.HttpSession;

public class ControllerSession {
	HttpSession session;
	
	
	
	public ControllerSession(HttpSession session) {
		super();
		this.session = session;
	}



	public Object getAttribute(String name){
		return session.getAttribute(name);
	}
}
