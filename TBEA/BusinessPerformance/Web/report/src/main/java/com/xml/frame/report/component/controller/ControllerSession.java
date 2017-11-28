package com.xml.frame.report.component.controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import com.frame.script.el.PackingMap;
import com.frame.script.util.PropMap;

public class ControllerSession extends PropMap{
	HttpSession session;
	
	
	
	public ControllerSession(HttpSession session) {
		super();
		this.session = session;
	}

	@Override
	public Object getProperty(Object key) throws Exception {
		if ("timeout".equals(key)) {
			return new PackingMap(this, null).get(key);
		}
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
	
	public Object timeout(String key, Integer sec) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		        public void run() {
		        	try {
		        		session.removeAttribute(key);
		        	}catch(Exception e) {
		        		
		        	}
		        }
		}, sec * 1000);
		return this;
	}
	
}
