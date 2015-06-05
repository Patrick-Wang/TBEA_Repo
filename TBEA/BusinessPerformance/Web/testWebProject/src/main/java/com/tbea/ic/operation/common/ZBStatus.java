package com.tbea.ic.operation.common;


public enum ZBStatus{
	NONE(null),
	APPROVED("已审核"),
	SUBMITTED("已提交"),
	SAVED("已保存"),
	APPROVED_2("内部已审核"),
	SUBMITTED_2("内部已提交");
	
	public String getValue(){
		return value;
	}

	public static ZBStatus valueOf(int tyOrd){
		ZBStatus[] types = ZBStatus.values();
		if (types.length > tyOrd){
			return types[tyOrd];
		}
		return null;
	}
	
	public static ZBStatus fromString(String value){
		if (null == value){
			return ZBStatus.NONE;
		}
		ZBStatus[] types = ZBStatus.values();
		for (ZBStatus type : types){
			if (value.equals(type.getValue())){
				return type;
			}
		}
		return null;
	}
	
	private String value;
	
	ZBStatus(String value) {
		this.value = value;
	}
}