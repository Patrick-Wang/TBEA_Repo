package com.tbea.ic.operation.controller.servlet.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.tbea.ic.operation.controller.servlet.account.ACL;
import com.tbea.ic.operation.model.entity.jygk.Account;

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
//		try{
//			
//			System.out.println(JSONArray.fromObject(session.getValueNames()).toString());
//			System.out.println("account " + account);
//			System.out.println("account name" + account.getName());
//			System.out.println("account id" + account.getId());
//		}catch(Exception e){
//			
//		}
	
		if (null != account && account.getName() != null && account.getId() != 0) {
			return true;
		}
		return false;
	}
	
	public static Account getAccount(HttpSession session) {
		if (null != session) {
			if (null != session.getAttribute("account")) {
				return (Account) session.getAttribute("account");
			}
		}
		return null;
	}
	
	public static void setAccount(HttpSession session, Account account) {
		if (null != session) {
			session.setAttribute("account", account);
		}
	}
	
	public static void setAcl(HttpSession session, ACL acl) {
		if (null != session) {
			session.setAttribute("_acl", acl);
		}
	}
	
	public static ACL getAcl(HttpSession session) {
		if (null != session) {
			return (ACL) session.getAttribute("_acl");
		}
		return null;
	}
}
