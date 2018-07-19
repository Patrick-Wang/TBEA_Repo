package com.xml.frame.report.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.util.tools.DateUtil;

public class EasyCalendar {
	
	Calendar cal;
	
	Map<Integer, Object> cache = new HashMap<Integer, Object>();
	
	private static int count = 0;
	private static final Integer KEY_Format = count++;
	private static final Integer KEY_Sunday = count++;
	private static final Integer KEY_Monday = count++;
	private static final Integer KEY_Tuesday = count++;
	private static final Integer KEY_Wednesday = count++;
	private static final Integer KEY_Thursday = count++;
	private static final Integer KEY_Friday = count++;
	private static final Integer KEY_Satuday = count++;
	private static final Integer KEY_LastWeek = count++;
	private static final Integer KEY_NextWeek = count++;
	private static final Integer KEY_LastMonth = count++;
	private static final Integer KEY_NextMonth = count++;
	private static final Integer KEY_LasyYear = count++;
	private static final Integer KEY_NextYear = count++;
	private static final Integer KEY_Date = count++;
	private static final Integer KEY_Timestamp = count++;
	private static final Integer KEY_SeasonStart = count++;
	private static final Integer KEY_SeasonMid = count++;
	private static final Integer KEY_SeasonEnd = count++;
	private static final Integer KEY_NextSeason = count++;
	private static final Integer KEY_LastSeason = count++;
	private static final Integer KEY_Format_Month = count++;
	private static final Integer KEY_Format_Second = count++;
	private static final Integer KEY_Format_MS = count++;

	
	private Object cache(Integer key, Object val){
		cache.put(key, val);
		return val;
	}

	public EasyCalendar from(String time){
	    return new EasyCalendar(Date.valueOf(time));
    }

 	public EasyCalendar getCurrent(){
		return new EasyCalendar();
	}
 	
 	public int minusByWeek(EasyCalendar ec){
 		int curWeek = this.getWeek();
 		int otherWeek = ec.getWeek();
 		int yearCount = this.getYear() - ec.getYear();
 		if(yearCount > 0){
 			int wkCount = ec.getYearWeekCount() - otherWeek + curWeek;
 			for (int i = 1; i < yearCount; ++i){
 				ec = ec.getNextYear();
 				wkCount += ec.getYearWeekCount();
 			}
 			return wkCount; 			
 		}else if(yearCount < 0){
 			int wkCount = this.getYearWeekCount() - curWeek + otherWeek;
 			for (int i = -1; i > yearCount; --i){
 				ec = ec.getLastYear();
 				wkCount += ec.getYearWeekCount();
 			}
 			return -1 * wkCount; 	
 		}else{
 			return curWeek - otherWeek;
 		}
 	}
	
	
 	public int getYearWeekCount(){
 		return cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
 	}
 	
 	public EasyCalendar getWeekStart(){
 		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		int weekCount = cal.get(Calendar.WEEK_OF_YEAR);
		EasyCalendar ec = null;
		if (weekCount == 1){
			ec = new EasyCalendar(cal);
			ec.setMonth(1);
			ec.setDay(1);
		}else{
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			ec = new EasyCalendar(cal);
		}
		return ec;
 	}
 	
 	public EasyCalendar getWeekEnd(){
 		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		int weekCount = cal.get(Calendar.WEEK_OF_YEAR);
		int maxCount = cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
		EasyCalendar ec = null;
		if (weekCount == maxCount){
			ec = new EasyCalendar(cal);
			ec.setMonth(12);
			ec.setDay(31);
		}else{
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			ec = new EasyCalendar(cal);
		}
		return ec;
 	}
 	
	public EasyCalendar(Calendar cal) {
		super();
		this.cal = cal;
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
	
	public String getMonthFormat(){
		if (cache.containsKey(KEY_Format_Month)){
			return (String) cache.get(KEY_Format_Month);
		}
		return (String) cache(KEY_Format_Month, DateUtil.month(this.getDate()));
	}
	
	public String getFormat(){
		if (cache.containsKey(KEY_Format)){
			return (String) cache.get(KEY_Format);
		}
		return (String) cache(KEY_Format, DateUtil.day(this.getDate()));
	}
	
	public String getSecondFormat(){
		if (cache.containsKey(KEY_Format_Second)){
			return (String) cache.get(KEY_Format_Second);
		}
		return (String) cache(KEY_Format_Second, DateUtil.second(this.getTimestamp()));
	}
	
	public String getMsFormat(){
		if (cache.containsKey(KEY_Format_MS)){
			return (String) cache.get(KEY_Format_MS);
		}
		return (String) cache(KEY_Format_MS, DateUtil.millsecond(this.getTimestamp()));
	}
	
	public EasyCalendar getSunday(){
		if (cache.containsKey(KEY_Sunday)){
			return (EasyCalendar) cache.get(KEY_Sunday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return (EasyCalendar) cache(KEY_Sunday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getMonday(){
		if (cache.containsKey(KEY_Monday)){
			return (EasyCalendar) cache.get(KEY_Monday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (EasyCalendar) cache(KEY_Monday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getTuesday(){
		if (cache.containsKey(KEY_Tuesday)){
			return (EasyCalendar) cache.get(KEY_Tuesday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		return (EasyCalendar) cache(KEY_Tuesday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getWednesday(){
		if (cache.containsKey(KEY_Wednesday)){
			return (EasyCalendar) cache.get(KEY_Wednesday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		return (EasyCalendar) cache(KEY_Wednesday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getThursday(){
		if (cache.containsKey(KEY_Thursday)){
			return (EasyCalendar) cache.get(KEY_Thursday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		return (EasyCalendar) cache(KEY_Thursday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getFriday(){
		if (cache.containsKey(KEY_Friday)){
			return (EasyCalendar) cache.get(KEY_Friday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return (EasyCalendar) cache(KEY_Friday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getSatuday(){
		if (cache.containsKey(KEY_Satuday)){
			return (EasyCalendar) cache.get(KEY_Satuday);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return (EasyCalendar) cache(KEY_Satuday, new EasyCalendar(cal));
	}
	
	public EasyCalendar getLastWeek(){
		if (cache.containsKey(KEY_LastWeek)){
			return (EasyCalendar) cache.get(KEY_LastWeek);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.DAY_OF_MONTH, -7);
		return (EasyCalendar) cache(KEY_LastWeek, new EasyCalendar(cal));
	}
	
	public Integer getWeek(){
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public Integer getWeekYear(){
		Integer year = getYear();
		if (getWeek() == 1 && getMonth() == 12){
			return year + 1;
		}
		return year;
	}
	
	public EasyCalendar getNextWeek(){
		if (cache.containsKey(KEY_NextWeek)){
			return (EasyCalendar) cache.get(KEY_NextWeek);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return (EasyCalendar) cache(KEY_NextWeek, new EasyCalendar(cal));
	}
	
	public EasyCalendar getLastMonth(){
		if (cache.containsKey(KEY_LastMonth)){
			return (EasyCalendar) cache.get(KEY_LastMonth);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, -1);
		return (EasyCalendar) cache(KEY_LastMonth, new EasyCalendar(cal));
	}
	
	public EasyCalendar getNextMonth(){
		if (cache.containsKey(KEY_NextMonth)){
			return (EasyCalendar) cache.get(KEY_NextMonth);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, 1);
		return (EasyCalendar) cache(KEY_NextMonth, new EasyCalendar(cal));
	}
	
	public EasyCalendar getLastSeason(){
		if (cache.containsKey(KEY_LastSeason)){
			return (EasyCalendar) cache.get(KEY_LastSeason);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, -3);
		return (EasyCalendar) cache(KEY_LastSeason, new EasyCalendar(cal));
	}
	
	public EasyCalendar getNextSeason(){
		if (cache.containsKey(KEY_NextSeason)){
			return (EasyCalendar) cache.get(KEY_NextSeason);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, 3);
		return (EasyCalendar) cache(KEY_NextSeason, new EasyCalendar(cal));
	}
	
	public EasyCalendar getSeasonStart(){
		if (cache.containsKey(KEY_SeasonStart)){
			return (EasyCalendar) cache.get(KEY_SeasonStart);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.MONTH, (getJdCount(this.getMonth()) - 1) * 3);
		return (EasyCalendar) cache(KEY_SeasonStart, new EasyCalendar(cal));
	}
	
	public EasyCalendar getSeasonMid(){
		if (cache.containsKey(KEY_SeasonMid)){
			return (EasyCalendar) cache.get(KEY_SeasonMid);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.MONTH, (getJdCount(this.getMonth()) - 1) * 3 + 1);
		return (EasyCalendar) cache(KEY_SeasonMid, new EasyCalendar(cal));
	}
	
	public EasyCalendar getSeasonEnd(){
		if (cache.containsKey(KEY_SeasonEnd)){
			return (EasyCalendar) cache.get(KEY_SeasonEnd);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.set(Calendar.MONTH, (getJdCount(this.getMonth()) - 1) * 3 + 2);
		return (EasyCalendar) cache(KEY_SeasonEnd, new EasyCalendar(cal));
	}
	
	public EasyCalendar getLastYear(){
		if (cache.containsKey(KEY_LasyYear)){
			return (EasyCalendar) cache.get(KEY_LasyYear);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.YEAR, -1);
		return (EasyCalendar) cache(KEY_LasyYear, new EasyCalendar(cal));
	}
	
	public EasyCalendar getYearBefore(int yearCount){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.YEAR, -1 * yearCount);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getYearAfter(int yearCount){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.YEAR, yearCount);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getMonthBefore(int monthCount){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, -1 * monthCount);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getMonthAfter(int monthCount){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.MONTH, monthCount);
		return new EasyCalendar(cal);
	}
	
	public EasyCalendar getNextYear(){
		if (cache.containsKey(KEY_NextYear)){
			return (EasyCalendar) cache.get(KEY_NextYear);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.cal.getTimeInMillis());
		cal.add(Calendar.YEAR, 1);
		return (EasyCalendar) cache(KEY_NextYear, new EasyCalendar(cal));
	}
	
	public EasyCalendar getDays(Integer day){
		Calendar caltmp = Calendar.getInstance();
		caltmp.setTimeInMillis(cal.getTimeInMillis());
		caltmp.set(Calendar.DAY_OF_MONTH, day);
		return new EasyCalendar(caltmp);
	}
	
	public EasyCalendar getLastDays(){
		Calendar caltmp = Calendar.getInstance();
		caltmp.setTimeInMillis(cal.getTimeInMillis());
		caltmp.set(Calendar.DAY_OF_MONTH, caltmp.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new EasyCalendar(caltmp);
	}
	
	public EasyCalendar getMonths(Integer month){
		Calendar caltmp = Calendar.getInstance();
		caltmp.setTimeInMillis(cal.getTimeInMillis());
		caltmp.set(Calendar.MONTH, month - 1);
		return new EasyCalendar(caltmp);
	}
	
	public Date getDate(){
		if (cache.containsKey(KEY_Date)){
			return (Date) cache.get(KEY_Date);
		}
		return (Date) cache(KEY_Date, new Date(cal.getTimeInMillis()));
	}
	
	public Long getTime(){
		return this.cal.getTimeInMillis();
	}
	
	public Timestamp getTimestamp(){
		if (cache.containsKey(KEY_Timestamp)){
			return (Timestamp) cache.get(KEY_Timestamp);
		}
		return (Timestamp) cache(KEY_Timestamp, new Timestamp(cal.getTimeInMillis()));
	}
	
	public int getSeason(){
		return getJdCount(this.getMonth());
	}
	
	public int getDay(){
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public int getMonth(){
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public int getYear(){
		return cal.get(Calendar.YEAR);
	}
	
	public EasyCalendar addMonth(int num){
		cal.add(Calendar.MONTH, num);
		cache.clear();
		return this;
	}
	
	public EasyCalendar addYear(int num){
		cal.add(Calendar.YEAR, num);
		cache.clear();
		return this;
	}
	
	public EasyCalendar setMonth(int month){
		cal.set(Calendar.MONTH, month - 1);
		cache.clear();
		return this;
	}
	
	public EasyCalendar setYear(int year){
		cal.set(Calendar.YEAR, year);
		cache.clear();
		return this;
	}
	
	public EasyCalendar setDay(int day){
		cal.set(Calendar.DAY_OF_MONTH, day);
		cache.clear();
		return this;
	}
	
	public Calendar getRealCalendar(){
		return cal;
	}
	
	public static int getJdCount(int month){
		return (month - 1) / 3 + 1;
	}
	
	public int getSeasonFirstMonth(){
		return (getJdCount(this.getMonth()) - 1) * 3 + 1;
	}
	
	public void setTime(Date d){
		cache.clear();
		this.cal.setTime(d);
	}


    public boolean lt(EasyCalendar ec, String type){
	    long time1 = this.getTime();
        long time2 = ec.getTime();
        int accuracy = 1;
        if ("month".equals(type)){
            if (this.getYear() < ec.getYear()){
                return true;
            }
            if (this.getYear() == ec.getYear()){
                return this.getMonth() < ec.getMonth();
            }
            return false;
        } else if ("day".equals(type)){
            accuracy = 1000 * 60 * 60 * 24;
        } else if ("hour".equals(type)){
            accuracy = 1000 * 60 * 60;
        } else if ("min".equals(type)){
            accuracy = 1000 * 60;
        } else if ("sec".equals(type)){
            accuracy = 1000;
        }
        return (time1 / accuracy) < (time2 / accuracy);
    }

    public boolean eq(EasyCalendar ec, String type){
        long time1 = this.getTime();
        long time2 = ec.getTime();
        int accuracy = 1;
        if ("month".equals(type)){
            return this.getYear() == ec.getYear() &&
                    this.getMonth() == ec.getMonth();
        } else if ("day".equals(type)){
            accuracy = 1000 * 60 * 60 * 24;
        } else if ("hour".equals(type)){
            accuracy = 1000 * 60 * 60;
        } else if ("min".equals(type)){
            accuracy = 1000 * 60;
        } else if ("sec".equals(type)){
            accuracy = 1000;
        }
        return (time1 / accuracy) == (time2 / accuracy);
    }

    public boolean gt(EasyCalendar ec, String type){
        return !lt(ec, type) && !eq(ec, type);
    }

}
