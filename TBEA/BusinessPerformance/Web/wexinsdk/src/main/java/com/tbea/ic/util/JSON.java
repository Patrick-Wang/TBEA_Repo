package com.tbea.ic.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSON {
	public static String stringify(Object obj){
		if (obj instanceof List || obj instanceof Object[]){
			JSONArray jo = JSONArray.fromObject(obj);
			return jo.toString();
		}else{
			JSONObject jo = JSONObject.fromObject(obj);
			List<String> depKeys = new ArrayList<String>();
			Iterator<String> it = jo.keys();
			while (it.hasNext()) {
				String key = it.next();
				try {
					Method method = obj.getClass().getMethod("get" + key.substring(0, 1).toUpperCase() + key.substring(1));
					
					if (null == method.invoke(obj)) {
						depKeys.add(key);
					}
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			for (String key : depKeys){
				jo.remove(key);
			}
			return jo.toString();
		}
	}
	
	public static JSONArray parseArray(String js){
		return JSONArray.fromObject(js);
	}
	
	public static JSONObject parse(String js){
		return JSONObject.fromObject(js);
	}
}
