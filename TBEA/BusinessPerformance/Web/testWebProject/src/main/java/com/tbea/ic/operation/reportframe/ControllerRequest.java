package com.tbea.ic.operation.reportframe;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.tbea.ic.operation.common.EasyCalendar;

public class ControllerRequest {
	HttpServletRequest req;
	public static class Paramater{
		String val;

		public Paramater(String val) {
			this.val = val;
		}
		
		public String getString(){
			return val;
		}
		
		public EasyCalendar getCalendar(){
			return new EasyCalendar(Date.valueOf(val));
		}

		public Integer getInt(){
			return Integer.valueOf(val);
		}
		
		public String toString(){
			return val;
		}
	}
	
	
	
	public ControllerRequest(HttpServletRequest req) {
		super();
		this.req = req;
	}



	public Paramater getParamater(String name){
		return new Paramater(req.getParameter(name));
	} 
}
