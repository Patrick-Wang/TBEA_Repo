package com.tbea.ic.greet.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineDetector implements HttpSessionListener{
	
	private static Map<String, HttpSession> onlineSessions = Collections.synchronizedMap(new HashMap<String, HttpSession>());
	private final static String ONLINE_TAG = "dector.online";
	public static boolean goOnline(HttpSession session){
		if(session != null){
			if (!isOnline(session)){
				session.setAttribute(ONLINE_TAG, true);
				return true;
			}
		}
		return false;
	}
	
	public static boolean goOffline(HttpSession session){
		if (isOnline(session)){
			session.invalidate();
			return true;
		}
		return false;
	}
	
	public static boolean isOnline(HttpServletRequest httpRequest){
		return isOnline(httpRequest.getSession(false));
	}
	
	public static boolean isOnline(HttpSession session){
		return session != null && 
				null != session.getAttribute(ONLINE_TAG) && 
				(Boolean)session.getAttribute(ONLINE_TAG);
	}

	public void sessionCreated(HttpSessionEvent event) {
		onlineSessions.put(event.getSession().getId(), event.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		event.getSession().setAttribute(ONLINE_TAG, false);
		onlineSessions.remove(event.getSession().getId());		
	}
}
