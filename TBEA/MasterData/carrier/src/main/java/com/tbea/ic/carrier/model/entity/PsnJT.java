package com.tbea.ic.carrier.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehrjt_person_info")
public class PsnJT extends Psn {
	
	@Id
	@Column(name = "身份证号")
	String id;

	@Column(name = "姓名")
	String psnname;
	
	@Column(name = "性别")
	String sex;

	@Column(name = "手机号")
	String mobile;
	
	@Column(name = "员工号")
	String psnNo;
	
	@Column(name = "单点登录名")
	String psnSSO;
	
//	@Column(name = "birthdate")
//	String birthdate;
//	
//	@Column(name = "addr")
//	String addr;
//	
//	@Column(name = "pk_corp")
//	String pk_corp;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPsnname() {
		return psnname;
	}

	public void setPsnname(String psnname) {
		this.psnname = psnname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPsnNo() {
		return psnNo;
	}

	public void setPsnNo(String psnNo) {
		this.psnNo = psnNo;
	}

	public String getPsnSSO() {
		return psnSSO;
	}
	
	public void setPsnSSO(String psnSSO) {
		this.psnSSO = psnSSO;
	}
	
//	public String getBirthdate() {
//		return birthdate;
//	}
//
//	public void setBirthdate(String birthdate) {
//		this.birthdate = birthdate;
//	}
//
//	public String getAddr() {
//		return addr;
//	}
//
//	public void setAddr(String addr) {
//		this.addr = addr;
//	}
//
//	public String getPk_corp() {
//		return pk_corp;
//	}
//
//	public void setPk_corp(String pk_corp) {
//		this.pk_corp = pk_corp;
//	}

	
}
