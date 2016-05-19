package com.tbea.ic.operation.common;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

public class DateSelection {
	private Calendar cal = Calendar.getInstance();
	private boolean hasMonth = true;
	private boolean hasDay = true;
	public DateSelection(Calendar c){
		if (null != c){
			this.cal = c;
		}
	}
	
	
	public static Date getStartDate(HttpServletRequest request){
		String year = request.getParameter("startYear");
		String month = request.getParameter("startMonth");
		String day = request.getParameter("startDay");
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
	
	public static List<Date> getDate(JSONArray years, JSONArray month) {
		List<Date> dateList = new ArrayList<Date>();
		for (int i = 0; i < years.size(); ++i) {
			StringBuilder date = new StringBuilder();

			date.append(years.getInt(i));
			date.append("-");
			date.append(month.getInt(i));
			date.append("-");
			date.append("1");
			dateList.add(Date.valueOf(date.toString()));

		}
		return dateList;
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
