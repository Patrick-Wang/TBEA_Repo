package com.tbea.ic.operation.reportframe.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DBUtil {

	static Map<String, EntityManager> ems = Collections.synchronizedMap(new HashMap<String, EntityManager>());
	
	public static EntityManager getEntityManager(EntityManagerFactory emf, String emName){
		if (!ems.containsKey(emName)){
			return ems.put(emName, emf.createEntityManager());
		}
		return ems.get(emName);
	}
	
}
