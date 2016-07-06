package com.tbea.ic.operation.reportframe.component.entity;

import java.util.HashMap;
import java.util.Map;

public class Context {
	Map<String, Object> objMap = new HashMap<String, Object>();
	public void put(String key, Object value){
		objMap.put(key, value);
	}
	
	public Object get(String key){
		return objMap.get(key);
	}
	
	public boolean contains(String key){
		return objMap.containsKey(key);
	}
}
