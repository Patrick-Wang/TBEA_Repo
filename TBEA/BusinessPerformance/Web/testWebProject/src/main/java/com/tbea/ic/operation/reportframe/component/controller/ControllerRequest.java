package com.tbea.ic.operation.reportframe.component.controller;

import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.EasyCalendar;

public class ControllerRequest {
	HttpServletRequest req;
	public static class Paramater{
		Object val;

		public Paramater(Object val) {
			this.val = val;
		}
		
		
		public JSONArray asJsonArray(){
			if (val instanceof JSONArray){
				return (JSONArray) val;
			}
			if (val instanceof String){
				return JSONArray.fromObject((String)val);
			}
			return null;
		}
		
		public EasyCalendar asCalendar(){
			return new EasyCalendar(Date.valueOf((String) val));
		}

		public Integer asInt(){
			return Integer.valueOf((String) val);
		}
		
		public Map asMap(){
			return (Map) val;
		}
		
		public String asString(){
			return val.toString();
		}
		
		public String toString(){
			return val.toString();
		}
	}
	
	
	
	public ControllerRequest(HttpServletRequest req) {
		super();
		this.req = req;
	}


	public ServletContext getServletContext(){
		return req.getSession().getServletContext();
	}
	
	public Paramater getParameter(String name){
		if ("parameters".equals(name)){
			Map mp = new HashMap();
			String key = null;
			Enumeration<String> keys = req.getParameterNames();
			while (keys.hasMoreElements()){
				key = keys.nextElement();
				mp.put(key, req.getParameter(key));
			}
			return new Paramater(mp);
		}
		return new Paramater(req.getParameter(name));
	}
}
