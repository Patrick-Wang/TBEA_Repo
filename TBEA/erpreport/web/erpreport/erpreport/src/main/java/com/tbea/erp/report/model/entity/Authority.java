package com.tbea.erp.report.model.entity;

import java.io.Serializable;

public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	String raw;
	String flexValue;
	String category;
	String companyName;
	String role;

	public static Authority parse(String auth, String companyName){
		String[] segments = auth.split("_");
		if (segments.length == 1){
			return new Authority(null, null, null, segments[0]).setRaw(auth);
		}else{
			return new Authority(
					segments[0].substring(0, 4),
					segments[0].substring(4),
					companyName,
					segments[2]).setRaw(auth);
		}

	}



	public Authority(String flexValue, String category, String companyName, String role) {
		this.flexValue = flexValue;
		this.category = category;
		this.companyName = companyName;
		this.role = role;
	}

	public String getRaw() {
		return raw;
	}

	public Authority setRaw(String raw) {
		this.raw = raw;
		return this;
	}



	public String getFlexValue() {
		return flexValue;
	}

	public String getCategory() {
		return category;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getRole() {
		return role;
	}

	public void setFlexValue(String flexValue) {
		this.flexValue = flexValue;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
