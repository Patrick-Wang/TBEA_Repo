package com.tbea.ic.operation.controller.servlet.cpzlqk;

public enum ByqBhgType {
	YBYSQFJYS("110kV及以上产品"), PBCP("配变产品");
	public static ByqBhgType valueOf(Integer ord) {
		if (0 == ord) {
			return ByqBhgType.YBYSQFJYS;
		}
		return ByqBhgType.PBCP;
	}
	
	public static ByqBhgType parse(String value) {
		if (YBYSQFJYS.val().equals(value)) {
			return ByqBhgType.YBYSQFJYS;
		}
		
		if (PBCP.val().equals(value)) {
			return ByqBhgType.PBCP;
		}
		return null;
	}
	
	ByqBhgType(String val){
		this.value = val;
	}

	private String value;
	public String val(){
		return value;
	}
	
	
}
