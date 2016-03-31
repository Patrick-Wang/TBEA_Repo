package com.tbea.ic.util.json;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("rawtypes")
public class JsonTypes {
	
	public static enum SupportedType{
		STRING,
		INT,
		DOUBLE,
		FLOAT,
		LONG,
		BOOLEAN,
		BIGDECIMAL,
		BYTE,
		SQL_DATE,
		UTIL_DATE,
		TIMESTAMP,
		ARRAY,
		LIST,
		OTHER
	}
	
	public static boolean isString(Class cls){
		return String.class.getName().equals(cls.getName());
	}
	
	public static boolean isInt(Class cls){
		return Integer.class.getName().equals(cls.getName()) || "int".equals(cls.getSimpleName());
	}
	
	public static boolean isDouble(Class cls){
		return Double.class.getName().equals(cls.getName()) || "double".equals(cls.getSimpleName());
	}
	
	public static boolean isFloat(Class cls){
		return Float.class.getName().equals(cls.getName()) || "float".equals(cls.getSimpleName());
	}
	
	public static boolean isLong(Class cls){
		return Long.class.getName().equals(cls.getName()) || "long".equals(cls.getSimpleName());
	}
	
	public static boolean isBoolean(Class cls){
		return Boolean.class.getName().equals(cls.getName()) || "boolean".equals(cls.getSimpleName());
	}
	
	public static boolean isBigDecimal(Class cls){
		return BigDecimal.class.getName().equals(cls.getName());
	}
	
	public static boolean isByte(Class cls){
		return Byte.class.getName().equals(cls.getName()) || "byte".equals(cls.getSimpleName());
	}
	
	public static boolean isSqlDate(Class cls){
		return java.sql.Date.class.getName().equals(cls.getName());
	}
	
	public static boolean isUtillDate(Class cls){
		return java.util.Date.class.getName().equals(cls.getName());
	}
	
	public static boolean isTimeStamp(Class cls){
		return Timestamp.class.getName().equals(cls.getName());
	}
	
	public static boolean isArray(Class cls){
		return cls.isArray();
	}
	
	public static boolean isList(Class<?> cls){
		return cls.isAssignableFrom(List.class);
	}
	
	public static SupportedType typeOf(Class<?> cls){
		if (isString(cls)){
			return SupportedType.STRING;
		}
		if (isInt(cls)){
			return SupportedType.INT;
		}
		if (isDouble(cls)){
			return SupportedType.DOUBLE;
		}
		if (isFloat(cls)){
			return SupportedType.FLOAT;
		}
		if (isLong(cls)){
			return SupportedType.LONG;
		}
		if (isBoolean(cls)){
			return SupportedType.BOOLEAN;
		}
		if (isBoolean(cls)){
			return SupportedType.BOOLEAN;
		}
		if (isBigDecimal(cls)){
			return SupportedType.BIGDECIMAL;
		}
		if (isByte(cls)){
			return SupportedType.BYTE;
		}
		if (isSqlDate(cls)){
			return SupportedType.SQL_DATE;
		}
		if (isUtillDate(cls)){
			return SupportedType.UTIL_DATE;
		}
		if (isTimeStamp(cls)){
			return SupportedType.TIMESTAMP;
		}
		if (isArray(cls)){
			return SupportedType.ARRAY;
		}
		if (isList(cls)){
			return SupportedType.LIST;
		}
		return SupportedType.OTHER;
	}
}
