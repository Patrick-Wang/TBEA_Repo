package com.tbea.test.testWebProject.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
	
	public static String financeFormat(String val){
		
		if (val.equals("--")){
			return val;
		}
		
		if (val.isEmpty()){
			return "";
		}
		
		int dot = val.lastIndexOf('.');
		String intPart = "";
		List<String> parts = null;
		boolean positive = (val.charAt(0) != '-');
		if (dot > 0){
			if (positive){
				intPart = val.substring(0, dot);
			} else{
				intPart = val.substring(1, dot);
			}
			
			parts = new ArrayList<String>(intPart.length() / 3 + 2);
			parts.add(val.substring(dot));
		}
		else {
			if (positive){
				intPart = val;
			} else{
				intPart = val.substring(1);
			}
			parts = new ArrayList<String>(intPart.length() / 3 + 1);
		}
		
		
		int leftLength = intPart.length();
		
		
		while (leftLength > 3){
			parts.add("," + intPart.substring(leftLength - 3, leftLength));
			leftLength -= 3;
		}
	
		parts.add(intPart.substring(0, leftLength));
		
		if (!positive){
			parts.add("-");
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = parts.size() - 1; i >= 0; --i){
			builder.append(parts.get(i));
		}
	
		
		return builder.toString();
	}
	
	public static String plus(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			
		}
		
		return (v2 + v1) + "";
	}
	
	public static String minus(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			
		}
		
		return (v1 - v2) + "";
	}
	
	
	public static String mult(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			return "--";
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			return "--";
		}
		return v2 * v1 + "";
	}
	
	public static String division(String base, String sub){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(base);
		}
		catch (Exception e){
			return "--";
		}
		try{
			v2 = Double.parseDouble(sub); 
		}
		catch (Exception e){
			return "--";
		}
		if (v1 == 0){
			return "--";
		}
		else{
			return v2 / v1 + "";
		}
	}
	
	public static String doubleFormat(String dv){
		double v1 = 0;
		try{
			v1 = Double.parseDouble(dv);
		}
		catch (Exception e){
			return "0.00";
		}
		return String.format("%.2f", v1);
	}
	
	public static class Escape{
		
		private Calendar start;
		private Calendar end;
		public Escape(){}
		
		public void start(){
			start = Calendar.getInstance();
			
		}
		
		public long end(String log){
			end = Calendar.getInstance();
			System.out.println(log + (end.getTimeInMillis() - start.getTimeInMillis()));
			return end.getTimeInMillis() - start.getTimeInMillis();
		}
	}
}
