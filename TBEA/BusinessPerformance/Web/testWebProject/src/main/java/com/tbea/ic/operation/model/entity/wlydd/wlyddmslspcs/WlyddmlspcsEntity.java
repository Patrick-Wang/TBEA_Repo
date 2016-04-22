package com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.identifier.ylfx.CpmcEntity;

@Entity
@Table(name = "ylfxgb_ylfx_wlyddmle_ncdr")
public class WlyddmlspcsEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	
	Integer nf;
	Integer yf;
	DWXX dwxx;
	Integer tjfs;
	CpmcEntity cpmc;
	Double cb;
	Double sr;
	Integer zt;
	
	public Integer getZt() {
		return zt;
	}


	public void setZt(Integer zt) {
		this.zt = zt;
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


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}


	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}


	public Integer getTjfs() {
		return tjfs;
	}


	public void setTjfs(Integer tjfs) {
		this.tjfs = tjfs;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpid")
	public CpmcEntity getCpmc() {
		return cpmc;
	}


	public void setCpmc(CpmcEntity cpmc) {
		this.cpmc = cpmc;
	}


	public Double getCb() {
		return cb;
	}


	public void setCb(Double cb) {
		this.cb = cb;
	}


	public Double getSr() {
		return sr;
	}


	public void setSr(Double sr) {
		this.sr = sr;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
