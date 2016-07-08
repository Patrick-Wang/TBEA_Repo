package com.tbea.ic.operation.reportframe.component.service;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceProxy extends AbstractRoutingDataSource {

	static Map<Long, Object> dsStubs = Collections.synchronizedMap(new HashMap<Long, Object>());
	
	public static void registerSub(Object ds){
		dsStubs.put(Thread.currentThread().getId(), ds);
	}
	
	public static void unregisterSub(){
		dsStubs.remove(Thread.currentThread().getId());
	}
	
	Object getDs(){
		return dsStubs.get(Thread.currentThread().getId());
	}
	
	
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return getDs();
	}

}
