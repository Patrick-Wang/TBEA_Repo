package com.xml.frame.report.component.controller;

import javax.servlet.http.HttpSession;

import com.frame.script.util.PropMap;

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
