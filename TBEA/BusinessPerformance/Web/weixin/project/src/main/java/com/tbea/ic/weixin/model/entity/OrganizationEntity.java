package com.tbea.ic.weixin.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


public class OrganizationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String oname;
	String pk;
	String fatherpk;
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getFatherpk() {
		return fatherpk;
	}
	public void setFatherpk(String fatherpk) {
		this.fatherpk = fatherpk;
	}
}
