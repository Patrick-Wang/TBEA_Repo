package com.tbea.ic.weixin.model.entity;

import java.io.Serializable;


public class OrganizationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String oname;
	String ocode;
	String fatherocod;
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getFatherocod() {
		return fatherocod;
	}
	public void setFatherocod(String fatherocod) {
		this.fatherocod = fatherocod;
	}

}
