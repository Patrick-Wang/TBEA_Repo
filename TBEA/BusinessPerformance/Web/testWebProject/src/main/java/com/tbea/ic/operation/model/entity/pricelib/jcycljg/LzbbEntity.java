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
@Table(name = "pricelib_jcycljg_lzbb")
public class LzbbEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double shag;
	Double ncwg;
	Double syag;
	Double xaag;
	Double wlmqbg;
	Double scjj;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getShag() {
		return shag;
	}
	public void setShag(Double shag) {
		this.shag = shag;
	}
	public Double getNcwg() {
		return ncwg;
	}
	public void setNcwg(Double ncwg) {
		this.ncwg = ncwg;
	}
	public Double getSyag() {
		return syag;
	}
	public void setSyag(Double syag) {
		this.syag = syag;
	}
	public Double getXaag() {
		return xaag;
	}
	public void setXaag(Double xaag) {
		this.xaag = xaag;
	}
	public Double getWlmqbg() {
		return wlmqbg;
	}
	public void setWlmqbg(Double wlmqbg) {
		this.wlmqbg = wlmqbg;
	}
	public Double getScjj() {
		return scjj;
	}
	public void setScjj(Double scjj) {
		this.scjj = scjj;
	}
	
}
