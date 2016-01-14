package com.tbea.ic.weixin.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


public class PersionEntity implements Serializable {


	private static final long serialVersionUID = 1L;
	String psncode;
	String pk_corp;
	String psnname;
	String psnclcod;
	String mobile;
	String sex;
	
	public String getPsncode() {
		return psncode;
	}
	public void setPsncode(String psncode) {
		this.psncode = psncode;
	}
	
	public String getPk_corp() {
		return pk_corp;
	}
	
	public void setUnitcode(String unicode) {
		this.pk_corp = unicode;
	}
	
	public void setPk_corp(String pk_corp) {
		this.pk_corp = pk_corp;
	}
	public String getPsnname() {
		return psnname;
	}
	public void setPsnname(String psnname) {
		this.psnname = psnname;
	}
	public String getPsnclcod() {
		return psnclcod;
	}
	public void setPsnclcod(String psnclcod) {
		this.psnclcod = psnclcod;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
