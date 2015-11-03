package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import com.tbea.ic.operation.model.entity.jygk.DWXX;

public class YSDAILYPK implements Serializable {

	private static final long serialVersionUID = 1L;

	DWXX dwxx;
	
	Date date;

	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
