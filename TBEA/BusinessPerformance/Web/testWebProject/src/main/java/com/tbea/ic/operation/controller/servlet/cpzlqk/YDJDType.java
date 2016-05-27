package com.tbea.ic.operation.controller.servlet.cpzlqk;

public enum YDJDType {
	YD("月度"),
	JD("季度");
	public static YDJDType valueOf(Integer ord){
		if (0 == ord){
			return YDJDType.YD;
		}
		return YDJDType.JD;
	}
	
	YDJDType(String val){
		this.value = val;
	}

	private String value;
	
	public String val(){
		return value;
	}
}
