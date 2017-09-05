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
	
	@Override
	public Object put(Object key, Object value) {
		session.setAttribute((String) key, value);
		return this;
	}
	
	@Override
	public Object remove(Object key) {
		Object val = session.getAttribute((String) key);
		session.removeAttribute((String) key);
		return val;
	}

}
