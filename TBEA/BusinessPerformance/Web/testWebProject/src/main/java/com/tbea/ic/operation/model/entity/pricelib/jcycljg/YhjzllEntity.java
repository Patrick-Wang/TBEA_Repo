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
@Table(name = "pricelib_jcycljg_yhjzll")
public class YhjzllEntity extends AbstractReadWriteEntity implements
		Serializable {
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
	Double dk6gyn;
	Double dk6gyz1n;
	Double dk1z3n;
	Double ckhq;
	Double ckdqbn;
	Double ckdqyn;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getDk6gyn() {
		return dk6gyn;
	}

	public void setDk6gyn(Double dk6gyn) {
		this.dk6gyn = dk6gyn;
	}

	public Double getDk6gyz1n() {
		return dk6gyz1n;
	}

	public void setDk6gyz1n(Double dk6gyz1n) {
		this.dk6gyz1n = dk6gyz1n;
	}

	public Double getDk1z3n() {
		return dk1z3n;
	}

	public void setDk1z3n(Double dk1z3n) {
		this.dk1z3n = dk1z3n;
	}

	public Double getCkhq() {
		return ckhq;
	}

	public void setCkhq(Double ckhqckhq) {
		this.ckhq = ckhqckhq;
	}

	public Double getCkdqbn() {
		return ckdqbn;
	}

	public void setCkdqbn(Double ckdqbn) {
		this.ckdqbn = ckdqbn;
	}

	public Double getCkdqyn() {
		return ckdqyn;
	}

	public void setCkdqyn(Double ckdqyn) {
		this.ckdqyn = ckdqyn;
	}

}
