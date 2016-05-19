package com.tbea.ic.operation.model.entity.pricelib.jcycljg;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "pricelib_jcycljg_pvcsz")
public class PVCSzEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double tzyh;
	Double hnzh;
	Double sxys;
	Double hljhhhg;
	Double hnyh;
	Double sxbyhg;
	Double ybty;
	Double tjdhh;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getTzyh() {
		return tzyh;
	}
	public void setTzyh(Double tzyh) {
		this.tzyh = tzyh;
	}
	public Double getHnzh() {
		return hnzh;
	}
	public void setHnzh(Double hnzh) {
		this.hnzh = hnzh;
	}
	public Double getSxys() {
		return sxys;
	}
	public void setSxys(Double sxys) {
		this.sxys = sxys;
	}
	public Double getHljhhhg() {
		return hljhhhg;
	}
	public void setHljhhhg(Double hljhhhg) {
		this.hljhhhg = hljhhhg;
	}
	public Double getHnyh() {
		return hnyh;
	}
	public void setHnyh(Double hnyh) {
		this.hnyh = hnyh;
	}
	public Double getSxbyhg() {
		return sxbyhg;
	}
	public void setSxbyhg(Double sxbyhg) {
		this.sxbyhg = sxbyhg;
	}
	public Double getYbty() {
		return ybty;
	}
	public void setYbty(Double ybty) {
		this.ybty = ybty;
	}
	public Double getTjdhh() {
		return tjdhh;
	}
	public void setTjdhh(Double tjdhh) {
		this.tjdhh = tjdhh;
	}
	

}
