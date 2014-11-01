package com.tbea.test.testWebProject.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yqkqsbhb")
public class YQKBHQS extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	String Ny;//	年月
	double Yyn;//	逾期1个月以内
	double Ydsy;//	逾期1-3月
	double Sdly;//	逾期3-6月
	double Ldsey;// 逾期6-12月
	double Ynys;//	逾期1年以上


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name = "ny")
	public String getNy() {
		return Ny;
	}

	/**
	 * @param ny
	 *            the ny to set
	 */
	public void setNy(String ny) {
		Ny = ny;
	}

	@Column(name = "yq1yyn")
	public double getYyn() {
		return Yyn;
	}

	@Column(name = "yq1_3y")
	public double getYdsy() {
		return Ydsy;
	}

	@Column(name = "yq3_6y")
	public double getSdly() {
		return Sdly;
	}

	@Column(name = "yq6_12y")
	public double getLdsey() {
		return Ldsey;
	}

	@Column(name = "yq1nys")
	public double getYnys() {
		return Ynys;
	}

	/**
	 * @param yyn the yyn to set
	 */
	public void setYyn(double yyn) {
		Yyn = yyn;
	}

	/**
	 * @param ydsy the ydsy to set
	 */
	public void setYdsy(double ydsy) {
		Ydsy = ydsy;
	}

	/**
	 * @param sdly the sdly to set
	 */
	public void setSdly(double sdly) {
		Sdly = sdly;
	}

	/**
	 * @param ldsey the ldsey to set
	 */
	public void setLdsey(double ldsey) {
		Ldsey = ldsey;
	}

	/**
	 * @param ynys the ynys to set
	 */
	public void setYnys(double ynys) {
		Ynys = ynys;
	}

	

}
