package com.tbea.ic.operation.common;

import net.sf.json.JSONObject;

public class BMResponse {
	private Integer errorCode;
	private String message = "";
	
	public BMResponse(ErrorCode errorCode, String message) {
		super();
		this.errorCode = errorCode.ordinal();
		this.message = message;
	}
	
	public BMResponse(String message) {
		super();
		this.message = message;
	}
	
	public BMResponse(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode.ordinal();
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String toJson(){
		return JSONObject.fromObject(this).toString();
	}
}
