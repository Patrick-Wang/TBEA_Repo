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
@Table(name = "pricelib_jcycljg_tks")
public class TksEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double sxdx;
	Double lnly;
	Double sdzb;
	Double ahhq;
	Double qdgbxfk;
	Double ydfk;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getSxdx() {
		return sxdx;
	}

	public void setSxdx(Double sxdx) {
		this.sxdx = sxdx;
	}

	public Double getLnly() {
		return lnly;
	}

	public void setLnly(Double lnly) {
		this.lnly = lnly;
	}

	public Double getSdzb() {
		return sdzb;
	}

	public void setSdzb(Double sdzb) {
		this.sdzb = sdzb;
	}

	public Double getAhhq() {
		return ahhq;
	}

	public void setAhhq(Double ahhq) {
		this.ahhq = ahhq;
	}

	public Double getQdgbxfk() {
		return qdgbxfk;
	}

	public void setQdgbxfk(Double qdgbxfk) {
		this.qdgbxfk = qdgbxfk;
	}

	public Double getYdfk() {
		return ydfk;
	}

	public void setYdfk(Double ydfk) {
		this.ydfk = ydfk;
	}

}
