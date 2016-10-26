package com.tbea.ic.operation.model.entity.cpzlqk;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cpzlqk_zltjjg")
public class ZltjjgEntity extends AbstractReadWriteEntity implements Serializable {
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

	Integer nf;
	Integer yf;
	Integer dwid;
	Integer cpid;
	Integer bhgs;
	Integer zs;
	Timestamp xgsj;
	Timestamp shsj;
	Integer zt;
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	public Integer getYf() {
		return yf;
	}
	public void setYf(Integer yf) {
		this.yf = yf;
	}
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	public Integer getBhgs() {
		return bhgs;
	}
	public void setBhgs(Integer bhgs) {
		this.bhgs = bhgs;
	}
	public Integer getZs() {
		return zs;
	}
	public void setZs(Integer zs) {
		this.zs = zs;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	public Timestamp getShsj() {
		return shsj;
	}
	public void setShsj(Timestamp shsj) {
		this.shsj = shsj;
	}
}
