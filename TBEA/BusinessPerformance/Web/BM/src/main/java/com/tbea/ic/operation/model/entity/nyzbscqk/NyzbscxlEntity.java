package com.tbea.ic.operation.model.entity.nyzbscqk;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "nyzbscxl_xl")
public class NyzbscxlEntity extends AbstractReadWriteEntity implements Serializable {
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

	Integer dwid;
	Integer nf;
	Integer yf;
	Integer kq;
	Integer mz;
	Double xl;
	Integer zt;
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
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
	public Integer getKq() {
		return kq;
	}
	public void setKq(Integer kq) {
		this.kq = kq;
	}
	public Integer getMz() {
		return mz;
	}
	public void setMz(Integer mz) {
		this.mz = mz;
	}
	public Double getXl() {
		return xl;
	}
	public void setXl(Double xl) {
		this.xl = xl;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}

}
