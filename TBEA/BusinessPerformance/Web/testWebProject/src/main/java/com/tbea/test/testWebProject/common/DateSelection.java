package com.tbea.test.testWebProject.common;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

public class DateSelection {
	private Calendar cal = Calendar.getInstance();
	private boolean hasMonth = true;
	private boolean hasDay = true;
	
	public DateSelection(Calendar c){
		if (null != c){
			this.cal = c;
		}
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
		cal.set(year, month, day);
	}
	
	public DateSelection(int year, int month){
		this.cal = Calendar.getInstance();
		cal.set(year, month, 1);
		hasDay = false;
	}
	
	public DateSelection(int year){
		this.cal = Calendar.getInstance();
		cal.set(year, 1, 1);
		hasMonth = false;
		hasDay = false;
	}
	
	public DateSelection(){
		this.cal = Calendar.getInstance();
	}
	
	public void select(Map<String, Object> map){
		map.put("year", cal.get(Calendar.YEAR));
		if (hasMonth){
			map.put("month", cal.get(Calendar.MONTH) + 1);
			if (hasDay){
				map.put("day", cal.get(Calendar.DAY_OF_MONTH));
			}
		}
	}
	
}
