package com.tbea.ic.operation.reportframe.util;

import org.w3c.dom.Element;

public class TypeUtil {
	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static final int DATE = 3;
	public static int typeof(Element e){
		String type = e.getAttribute("type"); 
		if ("int".equalsIgnoreCase(type)){
			return INT;
		}
		if ("double".equalsIgnoreCase(type)){
			return DOUBLE;
		}
		
		if ("date".equalsIgnoreCase(type)){
			return DATE;
		}
		return STRING;
	}
}
