package com.tbea.ic.greet.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineService implements HttpSessionListener{
	
	private static Map<String, HttpSession> onlineSessions = Collections.synchronizedMap(new HashMap<String, HttpSession>());
	private final static String ONLINE_TAG = "detector.online";

	public static boolean goOnline(HttpServletRequest httpRequest) {
		if (!isOnline(httpRequest.getSession())) {
			httpRequest.getSession().setAttribute(ONLINE_TAG, true);
			return true;
		}
		return false;
	}
	
	public static boolean goOffline(HttpServletRequest httpRequest){
		if (isOnline(httpRequest.getSession(false))){
			httpRequest.getSession(false).invalidate();
			return true;
		}
		return false;
	}
	
	public static Map<String, HttpSession> getOnlineSessions(){
		return onlineSessions;
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
