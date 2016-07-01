package com.tbea.ic.operation.reportframe;

import org.w3c.dom.Element;

public class TypeUtil {
	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static int typeof(Element e){
		String type = e.getAttribute("type"); 
		if ("int".equalsIgnoreCase(type)){
			return INT;
		}
		if ("double".equalsIgnoreCase(type)){
			return DOUBLE;
		}
		
		return STRING;
	}
}
