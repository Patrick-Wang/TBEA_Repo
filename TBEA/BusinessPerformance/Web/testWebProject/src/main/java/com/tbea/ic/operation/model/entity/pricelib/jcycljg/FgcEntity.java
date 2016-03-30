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
@Table(name = "pricelib_jcycljg_fgc")
public class FgcEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double bj;
	Double tj;
	Double dl;
	Double ts;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getBj() {
		return bj;
	}
	public void setBj(Double bj) {
		this.bj = bj;
	}
	public Double getTj() {
		return tj;
	}
	public void setTj(Double tj) {
		this.tj = tj;
	}
	public Double getDl() {
		return dl;
	}
	public void setDl(Double dl) {
		this.dl = dl;
	}
	public Double getTs() {
		return ts;
	}
	public void setTs(Double ts) {
		this.ts = ts;
	}
	

}
