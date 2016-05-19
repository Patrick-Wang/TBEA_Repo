package com.tbea.ic.operation.model.entity.pricelib.jcycljg;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "pricelib_jcycljg_jt")
public class JtEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date date;
	Double sxll;
	Double hbxt;
	Double sdqd;
	Double hnjy;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getSxll() {
		return sxll;
	}
	public void setSxll(Double sxll) {
		this.sxll = sxll;
	}
	public Double getHbxt() {
		return hbxt;
	}
	public void setHbxt(Double hbxt) {
		this.hbxt = hbxt;
	}
	public Double getSdqd() {
		return sdqd;
	}
	public void setSdqd(Double sdqd) {
		this.sdqd = sdqd;
	}
	public Double getHnjy() {
		return hnjy;
	}
	public void setHnjy(Double hnjy) {
		this.hnjy = hnjy;
	}
	
}
