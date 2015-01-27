package com.tbea.ic.operation.common;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class DateSelection {
	private Calendar cal = Calendar.getInstance();
	private boolean hasMonth = true;
	private boolean hasDay = true;
	public DateSelection(Calendar c){
		if (null != c){
			this.cal = c;
		}
	}
	
	public static Date getDate(HttpServletRequest request){
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		StringBuilder date = new StringBuilder();
		if (year != null){
			date.append(year);
			date.append("-");
			date.append(month != null ? month : "1");
			date.append("-");
			date.append(day != null ? day : "1");
			return Date.valueOf(date.toString());
		}
		
		return null;
	}
	
	public Date getDate(){
		return Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH));
	}
	
	public DateSelection(Calendar c, boolean hasMonth, boolean hasDay){
		this(c);
		this.hasDay = hasDay;
		this.hasMonth = hasMonth;
	}
	
	public DateSelection(Date d){
		if (null != d){
			cal.setTime(d);
		}
	}
	
	public DateSelection(Date d, boolean hasMonth, boolean hasDay){
		this(d);
		this.hasDay = hasDay;
		this.hasMonth = hasMonth;
	}
	
	public DateSelection(int year, int month, int day){
		this.cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
	}
	
	public DateSelection(int year, int month){
		this(year, month, 1);
		hasDay = false;
	}
	
	public DateSelection(int year){
		this(year, 1, 1);
		hasMonth = false;
		hasDay = false;
	}
	
	public DateSelection(){
		
	}
	
	public void select(Map<String, Object> map){
		map.put("year", cal.get(Calendar.YEAR));
		if (hasMonth){
			map.put("month", cal.get(Calendar.MONTH) + 1);
			if (hasDay){
				int dayCount = cal.get(Calendar.DAY_OF_MONTH);
				map.put("day", dayCount);
				map.put("dayCount",  dayCount);
			}
		}
	}
	
}
