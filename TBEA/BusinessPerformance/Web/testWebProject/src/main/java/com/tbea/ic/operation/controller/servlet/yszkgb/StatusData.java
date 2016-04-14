package com.tbea.ic.operation.controller.servlet.yszkgb;

import java.util.List;

public class StatusData {
	boolean isReadOnly;
	List<List<String>> data;
	public StatusData(boolean isReadOnly, List<List<String>> data) {
		super();
		this.isReadOnly = isReadOnly;
		this.data = data;
	}
	public boolean isReadOnly() {
		return isReadOnly;
	}
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public List<List<String>> getData() {
		return data;
	}
	public void setData(List<List<String>> data) {
		this.data = data;
	}
	
}
