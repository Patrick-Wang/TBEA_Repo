package com.common;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static String financeFormat(String val){
		int dot = val.lastIndexOf('.');
		String intPart = "";
		List<String> parts = null;
		if (dot > 0){
			intPart = val.substring(0, dot);
			parts = new ArrayList<String>(intPart.length() / 3 + 2);
			parts.add(val.substring(dot));
		}
		else {
			intPart = val;
			parts = new ArrayList<String>(intPart.length() / 3 + 1);
		}
		
		int leftLength = intPart.length();
		if (intPart.charAt(0) == '-'){
			--leftLength;
		}
		
		
		while (leftLength > 3){
			parts.add("," + intPart.substring(leftLength - 3, leftLength));
			leftLength -= 3;
		}
		
		if (intPart.charAt(0) == '-'){
			++leftLength;
		}
		
		parts.add(intPart.substring(0, leftLength));
		StringBuilder builder = new StringBuilder();
		for (int i = parts.size() - 1; i >= 0; --i){
			builder.append(parts.get(i));
		}
		return builder.toString();
	}
}
