package com.tbea.ic.operation.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class EasyCalendar {
	
	public static class Days{
		EasyCalendar cal;

		public Days(EasyCalendar cal) {
			super();
			this.cal = cal;
		}
		
		public EasyCalendar getCalendar(){
			return cal;
		}
		
		public int count(){
			return cal.getRealCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		
		public EasyCalendar getDay(int day){
			Calendar caltmp = Calendar.getInstance();
			caltmp.setTimeInMillis(cal.getRealCalendar().getTimeInMillis());
			caltmp.set(Calendar.DAY_OF_MONTH, day);
			return new EasyCalendar(caltmp);
		}
	}
	
	public static class Months{
		EasyCalendar cal;

		public Months(EasyCalendar cal) {
			super();
			this.cal = cal;
		}
		
		public EasyCalendar getCalendar(){
			return cal;
		}
		
		public EasyCalendar getMonth(int month){
			Calendar caltmp = Calendar.getInstance();
			caltmp.setTimeInMillis(cal.getRealCalendar().getTimeInMillis());
			caltmp.set(Calendar.MONTH, month - 1);
			return new EasyCalendar(caltmp);
		}
	}
	
	Calendar cal;

	public EasyCalendar getCurrent(){
		return new EasyCalendar();
	}
	
	public EasyCalendar(Calendar cal) {
		super();
		this.cal = cal;
	}
	
	public void setTime(Date d){
		this.cal.setTime(d);
	}
	
	public EasyCalendar(int year, int month, int day) {
		this.cal = Calendar.getInstance();
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
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
	
	public String getFormat(){
		return Util.formatToDay(this.getDate());
	}
	
	public int getDay(){
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public EasyCalendar getLastSunday(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.DAY_OF_MONTH, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getSunday(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getMonday(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getLastMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, -1);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getLastYear(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.YEAR, -1);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getNextMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, 1);
		return new EasyCalendar(cal);
	}
	
	public Days getDays(){
		return new Days(this);
	}
	public Months getMonths(){
		return new Months(this);
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
	
	public EasyCalendar setYear(int year){
		cal.set(Calendar.YEAR, year);
		return this;
	}
	
	public EasyCalendar setDay(int day){
		cal.set(Calendar.DAY_OF_MONTH, day);
		return this;
	}
	
	public Calendar getRealCalendar(){
		return cal;
	}
	
	public Date getDate(){
		return new Date(cal.getTimeInMillis());
	}
	
	public Timestamp getTimestamp(){
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static int getJdCount(int month){
		return (month - 1) / 3 + 1;
	}
	
	public int getCurrentSeasonFirstMonth(){
		return (getJdCount(this.getMonth()) - 1) * 3 + 1;
	}
}
