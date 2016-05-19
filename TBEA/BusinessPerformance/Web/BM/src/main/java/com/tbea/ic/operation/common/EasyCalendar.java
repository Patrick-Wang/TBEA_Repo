package com.tbea.ic.operation.common;

import java.sql.Date;
import java.util.Calendar;

public class EasyCalendar {
	Calendar cal;

	public EasyCalendar(Calendar cal) {
		super();
		this.cal = cal;
	}
	
	public EasyCalendar() {
		this.cal = Calendar.getInstance();
	}
	
	public EasyCalendar(Date d) {
		super();
		this.cal = Calendar.getInstance();
		cal.setTime(d);
	}
	
	public EasyCalendar(java.util.Date d) {
		super();
		this.cal = Calendar.getInstance();
		cal.setTime(d);
	}
	
	public int getMonth(){
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public int getYear(){
		return cal.get(Calendar.YEAR);
	}
	
	public EasyCalendar addMonth(int num){
		cal.add(Calendar.MONTH, num);
		return this;
	}
	
	public EasyCalendar addYear(int num){
		cal.add(Calendar.YEAR, num);
		return this;
	}
	
	public EasyCalendar setMonth(int month){
		cal.set(Calendar.MONTH, month - 1);
		return this;
	}
	
	public Date getDate(){
		return new Date(cal.getTimeInMillis());
	}
	
}
