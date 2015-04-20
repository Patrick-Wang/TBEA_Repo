package com.tbea.ic.operation.common;


public enum ZBStatus{
	NONE,
	APPROVED,
	SUBMITTED,
	SAVED,
	APPROVED_2;
	public static ZBStatus valueOf(int tyOrd){
		ZBStatus[] types = ZBStatus.values();
		if (types.length > tyOrd){
			return types[tyOrd];
		}
		return null;
	}
}