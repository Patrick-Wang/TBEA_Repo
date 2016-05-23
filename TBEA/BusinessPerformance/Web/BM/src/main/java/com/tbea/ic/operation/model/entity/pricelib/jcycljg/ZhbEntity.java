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
@Table(name = "pricelib_jcycljg_zhb")
public class ZhbEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double shmg;
	Double njmg;
	Double gzsg;
	Double cspg;
	Double bjlg;
	Double sytg;
	Double wlmqbg;
	Double pjj;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getShmg() {
		return shmg;
	}

	public void setShmg(Double shmg) {
		this.shmg = shmg;
	}

	public Double getNjmg() {
		return njmg;
	}

	public void setNjmg(Double njmg) {
		this.njmg = njmg;
	}

	public Double getGzsg() {
		return gzsg;
	}

	public void setGzsg(Double gzsg) {
		this.gzsg = gzsg;
	}

	public Double getCspg() {
		return cspg;
	}

	public void setCspg(Double cspg) {
		this.cspg = cspg;
	}

	public Double getBjlg() {
		return bjlg;
	}

	public void setBjlg(Double bjlg) {
		this.bjlg = bjlg;
	}

	public Double getSytg() {
		return sytg;
	}

	public void setSytg(Double sytg) {
		this.sytg = sytg;
	}

	public Double getWlmqbg() {
		return wlmqbg;
	}

	public void setWlmqbg(Double wlmqbg) {
		this.wlmqbg = wlmqbg;
	}

	public Double getPjj() {
		return pjj;
	}

	public void setPjj(Double pjj) {
		this.pjj = pjj;
	}
}
