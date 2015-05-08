package com.tbea.ic.operation.controller.servlet.dashboard;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	private static List<HttpSession> sessions = new Vector<HttpSession>();
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		sessions.add(event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		sessions.remove(event.getSession());
	}

	public static List<HttpSession> getSessions(){
		return sessions;
	}
	
}
