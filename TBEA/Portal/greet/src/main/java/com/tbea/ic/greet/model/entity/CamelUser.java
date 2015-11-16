package com.tbea.ic.greet.model.entity;

public class CamelUser {
	String shortName;
	String userName;
	String password;
	public CamelUser(String shortName, String userName, String password) {
		super();
		this.shortName = shortName;
		this.password = password;
		this.userName = userName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
