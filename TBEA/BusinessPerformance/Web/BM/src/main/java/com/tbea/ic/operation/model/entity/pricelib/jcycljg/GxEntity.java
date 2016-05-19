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
@Table(name = "pricelib_jcycljg_gx")
public class GxEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double shsg;
	Double njjy;
	Double zzjy;
	Double tjtg;
	Double cdjg;
	Double pjj;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getShsg() {
		return shsg;
	}
	public void setShsg(Double shsg) {
		this.shsg = shsg;
	}
	public Double getNjjy() {
		return njjy;
	}
	public void setNjjy(Double njjy) {
		this.njjy = njjy;
	}
	public Double getZzjy() {
		return zzjy;
	}
	public void setZzjy(Double zzjy) {
		this.zzjy = zzjy;
	}
	public Double getTjtg() {
		return tjtg;
	}
	public void setTjtg(Double tjtg) {
		this.tjtg = tjtg;
	}
	public Double getCdjg() {
		return cdjg;
	}
	public void setCdjg(Double cdjg) {
		this.cdjg = cdjg;
	}
	public Double getPjj() {
		return pjj;
	}
	public void setPjj(Double pjj) {
		this.pjj = pjj;
	}
	
}
