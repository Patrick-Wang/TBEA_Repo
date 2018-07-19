package com.xml.frame.report.component.entity;

import java.util.HashMap;
import java.util.List;
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

	public Context clone(){
	    Context c = new Context();
	    for(String key : objMap.keySet()){
	        c.put(key, objMap.get(key));
        }
        return c;
    }

    public Context merge(Context c){
        for(String key : c.objMap.keySet()){
            objMap.put(key, c.objMap.get(key));
        }
        return this;
    }
}
