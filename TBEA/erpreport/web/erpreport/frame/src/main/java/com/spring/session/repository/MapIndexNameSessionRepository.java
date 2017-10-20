package com.spring.session.repository;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSessionRepository;
import org.springframework.web.context.ContextLoader;

public class MapIndexNameSessionRepository implements FindByIndexNameSessionRepository<ExpiringSession> {
	
	Map<String, ExpiringSession> sessionMap = new ConcurrentHashMap<String, ExpiringSession>();
	MapSessionRepository stub;

	public MapIndexNameSessionRepository() {
		stub = new MapSessionRepository(sessionMap);
	}

	public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
		stub.setDefaultMaxInactiveInterval(defaultMaxInactiveInterval);;
	}

	public void save(ExpiringSession session) {
		stub.save(session);
	}

	public ExpiringSession getSession(String id) {
		
		return stub.getSession(id);
	}

	public void delete(String id) {
//		HttpSessionListener sessionListener = ContextLoader.getCurrentWebApplicationContext().getBean(HttpSessionListener.class);
//		sessionListener.sessionDestroyed(new HttpSessionEvent(new MapHttpSessionWrapper(stub.getSession(id))));
		stub.delete(id);
	}

	public ExpiringSession createSession() {
//		HttpSessionListener sessionListener = ContextLoader.getCurrentWebApplicationContext().getBean(HttpSessionListener.class);
		ExpiringSession session = stub.createSession();
//		sessionListener.sessionCreated(new HttpSessionEvent(new MapHttpSessionWrapper(session)));
		return session;
	}

	@Override
	public Map<String, ExpiringSession> findByIndexNameAndIndexValue(String indexName, String indexValue) {
		Map<String, ExpiringSession> retMap = new HashMap<String, ExpiringSession>();
		for (ExpiringSession session : sessionMap.values()){
			if (indexValue.equals(session.getAttribute(indexName))){
				retMap.put(session.getId(), session);
			}
		}
		return retMap;
	}
	
	class MapHttpSessionWrapper implements HttpSession{
		ExpiringSession session;
		public MapHttpSessionWrapper(ExpiringSession session){
			this.session = session;
		}
		
		@Override
		public Object getAttribute(String arg0) {
			// TODO Auto-generated method stub
			return session.getAttribute(arg0);
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getCreationTime() {
			// TODO Auto-generated method stub
			return session.getCreationTime();
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return session.getId();
		}

		@Override
		public long getLastAccessedTime() {
			// TODO Auto-generated method stub
			return session.getLastAccessedTime();
		}

		@Override
		public int getMaxInactiveInterval() {
			// TODO Auto-generated method stub
			return session.getMaxInactiveIntervalInSeconds();
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HttpSessionContext getSessionContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getValue(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getValueNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void invalidate() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isNew() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void putValue(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeAttribute(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeValue(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAttribute(String arg0, Object arg1) {
			session.setAttribute(arg0, arg1);
		}

		@Override
		public void setMaxInactiveInterval(int arg0) {
			session.setMaxInactiveIntervalInSeconds(arg0);
		}
		
	}
	
}
