package com.frame.script.util;


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
	
	public static Integer findPair(String exp, int start, char open, char close){
		if (start < 0){
			return -1;
		}
		Integer stackCount = 0;
		for (int i = start + 1, len = exp.length(); i < len; ++i){
			if (exp.charAt(i) == open){
				++stackCount;
			}else if (exp.charAt(i) == close){
				if (0 == stackCount){
					return i + 1;
				}else{
					--stackCount;
				}
			}
		}
		return -1;
	}
	
}
