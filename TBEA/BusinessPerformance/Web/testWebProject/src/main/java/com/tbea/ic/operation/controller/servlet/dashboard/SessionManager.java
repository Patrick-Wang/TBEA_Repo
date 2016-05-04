package com.tbea.ic.operation.controller.servlet.dashboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.tbea.ic.operation.controller.servlet.account.ACL;
import com.tbea.ic.operation.model.entity.jygk.Account;

public class SessionManager implements HttpSessionListener {

	private static Map<String, HttpSession> onlineSessions = Collections.synchronizedMap(new HashMap<String, HttpSession>());

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		onlineSessions.put(event.getSession().getId(), event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		onlineSessions.remove(event.getSession().getId());
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
			if (null != session.getAttribute("_account")) {
				return (Account) session.getAttribute("_account");
			}
		}
		return null;
	}
	
	public static void setAccount(HttpSession session, Account account) {
		if (null != session) {
			session.setAttribute("_account", account);
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
