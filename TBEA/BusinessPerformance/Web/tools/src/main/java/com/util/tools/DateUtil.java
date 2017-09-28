package com.util.tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String format(String fmt, Date d) {
		if (d != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(fmt);
			return formatter.format(d);
		}
		return null;
	}
	
	// d => yyyyMM
	public static Date fromMonth1(String d) {
		return java.sql.Date.valueOf(d.substring(0, 4) + "-" + d.substring(4)
				+ "-1");
	}
	
	public static String month1(Date d) {
		return format("yyyyMM", d);
	}
	
	public static String month(Date d) {
		return format("yyyy-MM", d);
	}
	
	public static String day(Date d) {
		return format("yyyy-MM-dd", d);
	}

	public static String second(Timestamp d) {
		return format("yyyy-MM-dd HH:mm:ss", d);
	}
	
	public static String millsecond(Date d) {
		return format("yyyy-MM-dd HH:mm:ss.SSS", d);
	}

	public static java.sql.Date toDate(Calendar d){
		java.sql.Date ret = null;
		if (d != null){
				return java.sql.Date.valueOf(
							d.get(Calendar.YEAR) + "-" + 
							(d.get(Calendar.MONTH) + 1) + "-" + 
							d.get(Calendar.DAY_OF_MONTH));
		}
		return  ret;
	}
	
	public static java.sql.Date toDate(String date){
		java.sql.Date ret = null;
		if (date != null){
			try{//"yyyy-MM-dd"
				return java.sql.Date.valueOf(date);
			}catch(Exception e){
				
			}
			try{//"yyyy-MM"
				return java.sql.Date.valueOf(date + "-1");
			}catch(Exception e){
				
			}
			try{//"yyyy.MM.dd"
				return java.sql.Date.valueOf(date.replace('.', '-'));
			}catch(Exception e){
				
			}
			try{//"yyyy-MM-dd hh:mm:ss"
				if (date.length() >= "yyyy-MM-dd".length())
				return java.sql.Date.valueOf(date.substring(0, "yyyy-MM-dd".length()));
			}catch(Exception e){
				
			}
			try{//"yyyy/MM/dd"
				return java.sql.Date.valueOf(date.replace('/', '-'));
			}catch(Exception e){
				
			}
		}
		return  ret;
	}
}
