package com.tbea.test.testWebProject.common;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Util {
	public static String format(Date d){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM"); 
		return formatter.format(d);
	}
	//d => yyyyMM
	public static Date valueOf(String d){
		return java.sql.Date.valueOf(d.substring(0, 4) + "-" + d.substring(4) + "-1");
	}
	
	public static Integer valueOf(Integer v){
		return v == null ? Integer.valueOf(0) : v;
	}
	
	public static Double valueOf(Double v){
		return v == null ? Double.valueOf(0) : v;
	}
}
