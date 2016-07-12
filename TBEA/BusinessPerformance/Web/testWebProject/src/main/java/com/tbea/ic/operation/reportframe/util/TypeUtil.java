package com.tbea.ic.operation.reportframe.util;

import org.w3c.dom.Element;

public class TypeUtil {
	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static final int SQLDATE = 3;
	public static final int OBJECT = 4;
	public static int typeof(Element e){
		return typeof(e.getAttribute("type"));
	}
	
	public static int typeof(String type){
		if ("int".equalsIgnoreCase(type)){
			return INT;
		}
		
		if ("double".equalsIgnoreCase(type)){
			return DOUBLE;
		}
		
		if ("date".equalsIgnoreCase(type)){
			return SQLDATE;
		}
		
		if ("object".equalsIgnoreCase(type)){
			return OBJECT;
		}
		return STRING;
	}
	
	public static boolean isInt(Class<?> cls){
		return cls.isAssignableFrom(Integer.class) || cls.getName().equals("int");
	}
	
	public static boolean isDouble(Class<?> cls){
		return cls.isAssignableFrom(Double.class) || cls.getName().equals("double");
	}
	
	public static boolean isString(Class<?> cls){
		return cls.isAssignableFrom(String.class);
	}
	
	public static boolean instanceOf(Object obj, Class<?> cls2){
		return obj.getClass().isAssignableFrom(cls2);
	}
}
