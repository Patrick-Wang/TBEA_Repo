package com.common;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static String financeFormat(String val){
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
}
