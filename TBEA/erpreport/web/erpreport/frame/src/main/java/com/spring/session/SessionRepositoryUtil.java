package com.spring.session;

import java.util.Map;

import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.context.ContextLoader;

public class SessionRepositoryUtil {
	
	final public static String SPRING_SESSION_ONLINE_INDEX = FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;
	final public static String SPRING_SESSION_ONLINE_NAME = "ONLINE";
	
	public static Map<String, ExpiringSession> getSessions(){
		return getSessions(SPRING_SESSION_ONLINE_NAME);
	}
	
	public static Map<String, ExpiringSession> getSessions(String indexValue){
		FindByIndexNameSessionRepository<ExpiringSession> rep = ContextLoader.getCurrentWebApplicationContext().getBean(FindByIndexNameSessionRepository.class);
		return rep.findByIndexNameAndIndexValue(SPRING_SESSION_ONLINE_INDEX, indexValue);
	}
}
