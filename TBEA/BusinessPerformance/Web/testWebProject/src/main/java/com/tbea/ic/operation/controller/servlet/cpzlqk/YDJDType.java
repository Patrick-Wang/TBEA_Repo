package com.tbea.ic.operation.controller.servlet.cpzlqk;

public enum YDJDType {
	YD,
	JD;
	public static YDJDType valueOf(Integer ord){
		if (0 == ord){
			return YDJDType.YD;
		}
		return YDJDType.JD;
	}
}
