package com.tbea.erp.report.controller.servlet.session;

import com.tbea.erp.report.model.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

public class SessionManager implements HttpSessionListener {
	
	private static Map<String, HttpSession> onlineSessions = Collections.synchronizedMap(new HashMap<String, HttpSession>());
	private static List<OnSessionChangedListener> listeners = Collections.synchronizedList(new ArrayList<OnSessionChangedListener>());

	public static void onChange(OnSessionChangedListener listener){
		listeners.add(listener);
	}

	public interface OnSessionChangedListener{
		void onCreated(HttpSession session);
		void onDestroyed(HttpSession session);
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		synchronized (listeners) {
			Iterator<OnSessionChangedListener> it = listeners.iterator();
	        while (it.hasNext()){
	        	try{
	        		it.next().onCreated(session);
	        	}catch(Exception e){
	        		
	        	}
	        }
	    }
		onlineSessions.put(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		synchronized (listeners) {
			Iterator<OnSessionChangedListener> it = listeners.iterator();
	        while (it.hasNext()){
	        	try{
	        		it.next().onDestroyed(session);
	        	}catch(Exception e){
	        		
	        	}
	        	
	        }
	    }
		onlineSessions.remove(session.getId());
	}

	public static Map<String, HttpSession> getOnlineSessions(){
		return onlineSessions;
	}
	
	public static boolean isOnline(HttpSession session) {
		Account account = getAccount(session);
		if (null != account && account.getName() != null) {
			return true;
		}
		return false;
	}

	public static boolean isOnline(HttpServletRequest request) {
		return isOnline(request.getSession(false));
	}

	public static void destorySession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (null != session){
			session.invalidate();
		}
	}
	
	public static Account getAccount(HttpSession session) {
		if (null != session) {
			if (null != session.getAttribute(SessionKey.ACCOUNT)) {
				return (Account) session.getAttribute(SessionKey.ACCOUNT);
			}
		}
		return null;
	}
	
	public static void setAccount(HttpSession session, Account account) {
		if (null != session) {
			session.setAttribute(SessionKey.ACCOUNT, account);
		}
	}

	public static HttpSession createSession(HttpServletRequest request, Account account) {
		HttpSession session = request.getSession(true);
		setAccount(session, account);
		return session;
	}
}
