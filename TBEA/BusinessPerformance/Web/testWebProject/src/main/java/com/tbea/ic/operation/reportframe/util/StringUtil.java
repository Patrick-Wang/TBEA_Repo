package com.tbea.ic.operation.reportframe.util;


public class StringUtil {

	public static String trim(String text){
		if (null != text){
			return text.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
		}
		return null;
	}
	
	public static String shrink(String text){
		if (null != text){
			return text.replaceAll("\\s+", "");
		}
		return null;
	}
	
}
