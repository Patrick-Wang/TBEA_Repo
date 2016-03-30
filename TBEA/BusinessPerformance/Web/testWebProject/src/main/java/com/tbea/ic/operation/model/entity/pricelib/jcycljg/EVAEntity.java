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
@Table(name = "pricelib_jcycljg_eva")
public class EVAEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double bjyj142;
	Double bjyj183;
	Double ybV5110J;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getBjyj142() {
		return bjyj142;
	}
	public void setBjyj142(Double bjyj142) {
		this.bjyj142 = bjyj142;
	}
	public Double getBjyj183() {
		return bjyj183;
	}
	public void setBjyj183(Double bjyj183) {
		this.bjyj183 = bjyj183;
	}
	public Double getYbV5110J() {
		return ybV5110J;
	}
	public void setYbV5110J(Double ybV5110J) {
		this.ybV5110J = ybV5110J;
	}
		

}
