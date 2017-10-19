package com.frame.script.config;

public class ColType {
	public final static int TEXT = 0;
	public final static int NUMBER = 1;
	public final static int PERCENT = 2;
	public final static int STRING = 3;
	public final static int DATE = 4;
	public final static int DATETIME = 5;
	public final static int CURRENCY_SYMBOL = 6;
	public final static int INTEGER = 7;
	public final static int OPTION = 8;
	
	public final static Integer PRIMARY = 8;
	public final static Integer UNIQUE = 9;
	public final static Integer NOTNULL = 10;
	
	String sqlVal;
	Integer type;
	Integer constraint;
	String defaultVal;
	public String getSqlVal() {
		return sqlVal;
	}


	public Integer getType() {
		return type;
	}


	public Integer getConstraint() {
		return constraint;
	}


	public void setConstraint(Integer constraint) {
		this.constraint = constraint;
	}

	public ColType(String sqlVal, Integer type, Integer constraint, String defaultVal) {
		super();
		this.sqlVal = sqlVal;
		this.type = type;
		this.constraint = constraint;
		this.defaultVal = defaultVal;
	}


	public String getDefaultVal() {
		if (this.type == OPTION) {
			if (defaultVal != null) {
				int index = defaultVal.indexOf("(");
				if (index > 0) {
					return defaultVal.substring(0, index);
				}
			}
		}
		return defaultVal;
	}
	
	public String getOptions() {
		if (this.type == OPTION) {
			if (defaultVal != null) {
				int start = defaultVal.indexOf("(");
				int end = defaultVal.lastIndexOf(")");
				if (start >= 0 && end > start) {
					return defaultVal.substring(start + 1, end);
				}
			}
		}
		return "";
	}


	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}


	private final static Integer toInt(String size) {
		try {
			return Integer.valueOf(size);
		}catch(Exception e) {
			return null;
		}		
	} 
	
	private final static Integer[] toNumberSize(String size) {
		Integer[] numSize= new Integer[] {
				18, 4
		};
		if (size != null) {
			String[] sSz = size.split(",");
			if (sSz.length > 0) {
				Integer tmpTotal = toInt(sSz[0]);
				if (null != tmpTotal) {
					numSize[0] = tmpTotal;
				}
			}
			if (sSz.length > 1) {
				Integer tmpDot = toInt(sSz[1]);
				if (null != tmpDot) {
					numSize[1] = tmpDot;
				}
			}
		}
		return numSize;
	}
	
	private final static Integer getConstraint(String constraint) {
		if ("主键".equals(constraint)) {
			return ColType.PRIMARY;
		}
		if ("唯一".equals(constraint)) {
			return ColType.UNIQUE;
		}
		if ("非空".equals(constraint)) {
			return ColType.NOTNULL;
		}
		return null;
	}
	
	public final static ColType parse(String type, String size, String constraint, String defaultVal) {
		Integer cons = getConstraint(constraint);
		if ("文字".equals(type)) {
			Integer sz = toInt(size);
			if ("不限".equals(size)) {
				return new ColType("text", ColType.TEXT, cons, defaultVal);
			}else {
				if (sz == null) {
					sz = 100;
				}
				return new ColType("varchar(" + sz + ")", ColType.STRING, cons, defaultVal);
			}
		}else if ("文本格式".equals(type)) {
			return new ColType("text", ColType.TEXT, cons, defaultVal);
		}else if ("日期".equals(type)) {
			return new ColType("date", ColType.DATE, cons, defaultVal);
		}else if ("时间".equals(type)) {
			return new ColType("datetime", ColType.DATETIME, cons, defaultVal);
		}else if ("数值".equals(type)) {
			Integer[] numSize = toNumberSize(size);
			return new ColType("numeric(" + numSize[0] + ", " + numSize[1] + ")", ColType.NUMBER, cons, defaultVal);
		}else if ("整数".equals(type)) {
			return new ColType("int", ColType.INTEGER, cons, defaultVal);
		}else if ("百分数".equals(type)) {
			Integer[] numSize = toNumberSize(size);
			return new ColType("numeric(" + numSize[0] + ", " + numSize[1] + ")", ColType.PERCENT, cons, defaultVal);
		}else if ("货币符号".equals(type)) {
			Integer sz = toInt(size);
			if (sz == null) {
				sz = 10;
			}
			return new ColType("varchar(" + sz + ")", ColType.CURRENCY_SYMBOL, cons, defaultVal);
		}else if ("值集".equals(type)) {
			Integer sz = toInt(size);
			if (sz == null) {
				sz = 100;
			}
			return new ColType("varchar(" + sz + ")", ColType.OPTION, cons, defaultVal);
		}
		return null;
	}
	
}
