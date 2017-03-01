package com.tbea.ic.operation.reportframe.component.entity;

import java.util.HashMap;
import java.util.Map;

public class Context {
	Map<String, Object> objMap =new HashMap<String, Object>();
	public void put(String key, LasyObject value){
		objMap.put(key, value);
	}
	
	public void put(String key, Object value){
		objMap.put(key, value);
	}
	
	public Object get(String key){
		Object obj = objMap.get(key);
		if (obj instanceof LasyObject){
			obj = ((LasyObject)obj).getObject();
			objMap.put(key, obj);
		}
		return obj;
	}
	
	public boolean contains(String key){
		return objMap.containsKey(key);
	}
	
	public Object remove(String key){
		return objMap.remove(key);
	}
}
