package com.tbea.ic.operation.reportframe.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DBUtil {

	static Map<String, EntityManager> ems = Collections.synchronizedMap(new HashMap<String, EntityManager>());
	
	public final static int SQL_RET_TABLE = 0;
	public final static int SQL_RET_VALUE = 1;
	public final static int SQL_RET_EMPTY = 2;
	
	public static EntityManager getEntityManager(EntityManagerFactory emf, String emName){
		if (!ems.containsKey(emName)){
			return ems.put(emName, emf.createEntityManager());
		}
		return ems.get(emName);
	}
	
	public static Object transform(Object obj) {
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				obj = ((BigDecimal) obj).doubleValue();
			} else if (obj instanceof Long) {
				obj = ((Long) obj).intValue();
			} else if (obj instanceof BigInteger) {
				obj = ((BigInteger) obj).intValue();
			} else if (obj instanceof Date) {
				obj = new java.util.Date(((Date) obj).getTime());
			}
		}
		return obj;
	}
	
	public static int trans2StandardType(List sqlRet){
		if (!sqlRet.isEmpty()) {
			if (null != sqlRet.get(0) && sqlRet.get(0).getClass().isArray()) {
				for (Object[] objs : (List<Object[]>) sqlRet) {
					for (int i = objs.length - 1; i >= 0; --i) {
						objs[i] = transform(objs[i]);
					}
				}
				return SQL_RET_TABLE;
			} else {
				for (int i = sqlRet.size() - 1; i >= 0; --i) {
					sqlRet.set(i, transform(sqlRet.get(i)));
				}
				return SQL_RET_VALUE;
			}
		}
		return SQL_RET_EMPTY;		
	}
}
