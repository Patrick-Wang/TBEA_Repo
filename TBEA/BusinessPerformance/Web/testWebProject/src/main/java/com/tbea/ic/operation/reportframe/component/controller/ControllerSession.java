package com.tbea.ic.operation.reportframe.component.controller;

import javax.servlet.http.HttpSession;

import com.tbea.ic.operation.common.PropMap;

public class ControllerSession extends PropMap{
	HttpSession session;
	
	public ControllerSession(HttpSession session) {
		super();
		this.session = session;
	}

	@Override
	public Object getProperty(Object key) throws Exception {
		return session.getAttribute((String)key);
	}
}
