package com.tbea.ic.operation.model.entity;

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
	Double Yyn;//	逾期1个月以内
	Double Ydsy;//	逾期1-3月
	Double Sdly;//	逾期3-6月
	Double Ldsey;// 逾期6-12月
	Double Ynys;//	逾期1年以上
	Integer qybh;
	/**
	 * @return the qybh
	 */
	@Column(name = "qybh")	
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

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
	public Double getYyn() {
		if (null == Yyn){
			return 0.00;
		}
		return Yyn;
	}

	@Column(name = "yq1_3y")
	public Double getYdsy() {
		if (null == Ydsy){
			return 0.00;
		}
		return Ydsy;
	}

	@Column(name = "yq3_6y")
	public Double getSdly() {
		if (null == Sdly){
			return 0.00;
		}
		return Sdly;
	}

	@Column(name = "yq6_12y")
	public Double getLdsey() {
		if (null == Ldsey){
			return 0.00;
		}
		return Ldsey;
	}

	@Column(name = "yq1nys")
	public Double getYnys() {
		if (null == Ynys){
			return 0.00;
		}
		return Ynys;
	}

	/**
	 * @param yyn the yyn to set
	 */
	public void setYyn(Double yyn) {
		Yyn = yyn;
	}

	/**
	 * @param ydsy the ydsy to set
	 */
	public void setYdsy(Double ydsy) {
		Ydsy = ydsy;
	}

	/**
	 * @param sdly the sdly to set
	 */
	public void setSdly(Double sdly) {
		Sdly = sdly;
	}

	/**
	 * @param ldsey the ldsey to set
	 */
	public void setLdsey(Double ldsey) {
		Ldsey = ldsey;
	}

	/**
	 * @param ynys the ynys to set
	 */
	public void setYnys(Double ynys) {
		Ynys = ynys;
	}

}
