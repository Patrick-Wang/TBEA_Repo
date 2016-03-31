package com.tbea.ic.scanner.net.entity;

public class ErrorMessage {
	private Integer errorCode;
	private String message;

	public ErrorMessage(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public ErrorMessage(String message) {
		super();
		this.message = message;
	}

	public ErrorMessage(Integer errorCode) {
		super();
		this.errorCode = errorCode;
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
}
