package com.tbea.ic.operation.common;

public enum ErrorCode {
	OK,
	DATABASE_EXCEPTION,
	PREMARY_KEY_CONFILICT;
	
	public static ErrorCode valueOf(int Id){
		ErrorCode[] types = ErrorCode.values();
		for (int i = types.length - 1; i >= 0; --i){
			if (types[i].ordinal() == Id){
				return types[i];
			}
		}
		return null;
	}
}
