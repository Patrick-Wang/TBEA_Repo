package com.tbea.ic.operation.reportframe;

import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tbea.ic.operation.common.EasyCalendar;

public class ControllerRequest {
	HttpServletRequest req;
	public static class Paramater{
		Object val;

		public Paramater(Object val) {
			this.val = val;
		}
		
		public String getString(){
			return (String) val;
		}
		
		public EasyCalendar getCalendar(){
			return new EasyCalendar(Date.valueOf((String) val));
		}

		public Integer getInt(){
			return Integer.valueOf((String) val);
		}
		
		public Map getMap(){
			return (Map) val;
		}
		
		public String toString(){
			return val.toString();
		}
	}
	
	
	
	public ControllerRequest(HttpServletRequest req) {
		super();
		this.req = req;
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
