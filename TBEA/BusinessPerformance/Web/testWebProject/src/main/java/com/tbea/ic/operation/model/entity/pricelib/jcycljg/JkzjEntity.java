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
@Table(name = "pricelib_jcycljg_jkzj")
public class JkzjEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double jndqzwz;
	Double jndyg;
	Double zljx;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getJndqzwz() {
		return jndqzwz;
	}
	public void setJndqzwz(Double jndqzwz) {
		this.jndqzwz = jndqzwz;
	}
	public Double getJndgy() {
		return jndyg;
	}
	public void setJndgy(Double jndyg) {
		this.jndyg = jndyg;
	}
	public Double getZljx() {
		return zljx;
	}
	public void setZljx(Double zljx) {
		this.zljx = zljx;
	}
	
}
