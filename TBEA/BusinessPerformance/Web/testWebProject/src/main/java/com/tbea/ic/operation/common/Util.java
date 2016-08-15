package com.tbea.ic.operation.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;

public class Util {
	
	public static String format(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format(d);
	}
	
	public static String formatToDay(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(d);
	}
	
	public static String formatToSecond(Timestamp d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return formatter.format(new Date(d.getTime()));
	}
	
	
	public static String formatToMonth(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(d);
	}

	
	public static List resize(List list, int size){
		if (list.size() < size){
			for (int i = list.size(); i < size; ++i){
				list.add(null);
			}
		}
		return list;
	}
	
	public static List resize(List list, int size, Object val){
		if (list.size() < size){
			for (int i = list.size(); i < size; ++i){
				list.add(val);
			}
		}
		return list;
	}
	
	// d => yyyyMM
	public static Date valueOf(String d) {
		return java.sql.Date.valueOf(d.substring(0, 4) + "-" + d.substring(4)
				+ "-1");
	}

	public static boolean isZero(Double val){
		return Math.abs(val) < 0.0000001;
	}
	
	public static boolean isPositive(Double val){
		return val > 0;
	}
	
	public static boolean isNegative(Double val){
		return val < 0;
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
	
	public static String toString(Double val){
		return val == null ? null : "" + val;
	}
	
	public static String toString(List<Company> comps){
		String ret = "";
		for (Company comp : comps){
			ret += "," + comp.getId();
		}
		return ret.substring(1);
	}
	
	public static String toNameString(List<Company> comps){
		String ret = "";
		for (Company comp : comps){
			ret += ",'" + comp.getName() + "'";
		}
		return ret.substring(1);
	}
	
	public static String toInteger(List<Integer> comps)
	{
		String ret = "";
		for(Integer comp : comps){
			ret += "," + comp;
		}
		return ret.substring(1);
	}
	
	public static String toSimpleString(List<String> arr)
	{
		String ret = "";
		for(String s : arr){
			ret += "," + s;
		}
		return ret.substring(1);
	}
	
	public static String toBMString(List<Company> comps){
		String ret = "";
		for (Company comp : comps){
			ret += "," + comp.getId();
		}
		return ret.substring(1);
	}
	
	public static String toBMSString(List<Company> comps){
		String ret = "";
		for (Company comp : comps){
			ret += ",'0" + comp.getId() + "'";
		}
		return ret.substring(1);
	}
	
	public static String toYear(List<Date> dateList){
		String ret = "";
		Calendar cal = Calendar.getInstance();
		for (Date d : dateList){
			cal.setTime(d);
			ret += ",0" + cal.get(Calendar.YEAR);
		}
		return ret.substring(1);
	}
	
	public static String toMonth(List<Date> dateList){
		String ret = "";
		Calendar cal = Calendar.getInstance();
		for (Date d : dateList){
			cal.setTime(d);
			ret += ",0" + cal.get(Calendar.MONTH);
		}
		return ret.substring(1);
	}
	
	public static int valueOf(Integer v) {
		if (null == v) {
			return 0;
		}
		return v;
	}

	public static int toInt(String val) {
		try {
			return Integer.valueOf(val);
		} catch (Exception e) {

		}
		return 0;
	}

	public static double toDouble(String val) {
		try {
			return Double.valueOf(val);
		} catch (Exception e) {
		}
		return 0;
	}

	public static Double valueOf(Double v) {
		if (null == v) {
			return 0.0;
		}
		return v;
	}
	
	public static Double toDoubleNull(String val) {
		try {
			return Double.valueOf(val);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Integer toIntNull(String val) {
		try {
			return Double.valueOf(val).intValue();
		} catch (Exception e) {
		}
		return null;
	}
	
	public static Double sum(Double[] vals){
		Double ret = null;
		for (int i = 0; i < vals.length; ++i){
			if (vals[i] != null){
				if (ret == null){
					ret = vals[i];
				}else{
					ret += vals[i];
				}
			}
		}
		return ret;
	}

	public static String financeFormat(String val) {

		if (val.equals("--")) {
			return val;
		}

		if (val.isEmpty()) {
			return "";
		}

		int dot = val.lastIndexOf('.');
		String intPart = "";
		List<String> parts = null;
		boolean positive = (val.charAt(0) != '-');
		if (dot > 0) {
			if (positive) {
				intPart = val.substring(0, dot);
			} else {
				intPart = val.substring(1, dot);
			}

			parts = new ArrayList<String>(intPart.length() / 3 + 2);
			parts.add(val.substring(dot));
		} else {
			if (positive) {
				intPart = val;
			} else {
				intPart = val.substring(1);
			}
			parts = new ArrayList<String>(intPart.length() / 3 + 1);
		}

		int leftLength = intPart.length();

		while (leftLength > 3) {
			parts.add("," + intPart.substring(leftLength - 3, leftLength));
			leftLength -= 3;
		}

		parts.add(intPart.substring(0, leftLength));

		if (!positive) {
			parts.add("-");
		}

		StringBuilder builder = new StringBuilder();
		for (int i = parts.size() - 1; i >= 0; --i) {
			builder.append(parts.get(i));
		}

		return builder.toString();
	}

	public static String plus(String val1, String val2, String val3, String val4) {
		return plus(plus(val1, val2, val3), val4);
	}

	public static String plus(String val1, String val2, String val3) {
		return plus(plus(val1, val2), val3);
	}

	public static String plus(String val1, String val2) {
		double v1 = 0;
		double v2 = 0;
		try {
			v1 = Double.parseDouble(val1);
		} catch (Exception e) {

		}
		try {
			v2 = Double.parseDouble(val2);
		} catch (Exception e) {

		}

		return (v2 + v1) + "";
	}

	public static String minus(String val1, String val2) {
		double v1 = 0;
		double v2 = 0;
		try {
			v1 = Double.parseDouble(val1);
		} catch (Exception e) {

		}
		try {
			v2 = Double.parseDouble(val2);
		} catch (Exception e) {

		}

		return (v1 - v2) + "";
	}

	public static String mult(String val1, String val2) {
		double v1 = 0;
		double v2 = 0;
		try {
			v1 = Double.parseDouble(val1);
		} catch (Exception e) {
			return "--";
		}
		try {
			v2 = Double.parseDouble(val2);
		} catch (Exception e) {
			return "--";
		}
		return v2 * v1 + "";
	}

	public static String division(String base, String sub) {
		double v1 = 0;
		double v2 = 0;
		try {
			v1 = Double.parseDouble(base);
		} catch (Exception e) {
			return "--";
		}
		try {
			v2 = Double.parseDouble(sub);
		} catch (Exception e) {
			return "--";
		}
		if (v1 == 0) {
			return "--";
		} else {
			return v2 / v1 + "";
		}
	}
	
	public static Double division(Double sub, Double base) {
		if (base == null || sub == null){
			return null;
		}
		
		if (isZero(base)){
			return null;
		}
		
		return sub / base;
	}

	public static String doubleFormat(String dv) {
		double v1 = 0;
		try {
			v1 = Double.parseDouble(dv);
		} catch (Exception e) {
			return "0.00";
		}
		return String.format("%.2f", v1);
	}

	public static class Elapse {

		private Calendar start;
		private Calendar end;

		public Elapse() {
		}

		public void start() {
			start = Calendar.getInstance();

		}

		public long end(String log) {
			end = Calendar.getInstance();
			System.out.println(log
					+ (end.getTimeInMillis() - start.getTimeInMillis()));
			return end.getTimeInMillis() - start.getTimeInMillis();
		}
	}

	private static boolean filter(CompanyType filters[],
			CompanyType val) {
		for (int i = filters.length - 1; i >= 0; --i) {
			if (val == filters[i]) {
				return true;
			}
		}
		return false;
	}

	public static String[][] getCompanyNameAndIds(List<Company> list,
			CompanyType[] filters) {
		List<String> names = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			if (!filter(filters, list.get(i).getType())) {
				ids.add(list.get(i).getType().ordinal() + "");
				names.add(list.get(i).getName());
			}
		}

		String[][] ret = new String[2][ids.size()];
		names.toArray(ret[0]);
		ids.toArray(ret[1]);
		return ret;
	}

	public static String[][] getCompanyNameAndIds(List<Company> list) {
		List<String> names = new ArrayList<String>();
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < list.size(); ++i) {
			ids.add(list.get(i).getType().ordinal() + "");
			names.add(list.get(i).getName());
		}
		String[][] ret = new String[2][ids.size()];
		names.toArray(ret[0]);
		ids.toArray(ret[1]);
		return ret;
	}
	
	public static String getSetMethodName(String property){
		return "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
	}
	
	public static byte[] response(ErrorCode val) throws UnsupportedEncodingException{
		return new BMResponse(val).toJson().getBytes("utf-8");
	}
	
	public static byte[] response(ErrorCode val, String msg) throws UnsupportedEncodingException{
		return new BMResponse(val, msg).toJson().getBytes("utf-8");
	}
	
	public static byte[] response(String val) throws UnsupportedEncodingException{
		return new BMResponse(val).toJson().getBytes("utf-8");
	}
	
	public static void correspond(Object beanFrom, Object beanTo){
		 Method[] methods = beanFrom.getClass().getMethods();
		 for (int i = 0; i < methods.length; ++i){
			 String name = methods[i].getName();
			 if (name.startsWith("get") && methods[i].getParameters().length == 0){
				 try {
					Method setMethod = beanTo.getClass().getMethod("set" + name.substring(3), methods[i].getReturnType());
					setMethod.invoke(beanTo, methods[i].invoke(beanFrom));
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		 }
	}

	public static Double minus(Double val, Double val2) {
		if (null != val && val2 != null){
			return val - val2;
		}
		return null;
	}

	public static List<String> toList(List<Double> hj) {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < hj.size(); ++i){
			ret.add("" + hj.get(i));
		}
		return ret;
	}
}
