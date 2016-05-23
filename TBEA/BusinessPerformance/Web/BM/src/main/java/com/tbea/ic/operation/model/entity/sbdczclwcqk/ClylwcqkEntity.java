package com.tbea.ic.operation.model.entity.sbdczclwcqk;

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

import com.tbea.ic.operation.model.entity.identifier.common.ClmcEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "sbdclczgb_cl_fcl")
public class ClylwcqkEntity extends AbstractReadWriteEntity implements Serializable {
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
	ClmcEntity clmc;
	Double cl;
	Integer zt;
	
	public Integer getNf() {
		return nf;
	}

	public void setNf(Integer nf) {
		this.nf = nf;
	}

	public Integer getTjfs() {
		return tjfs;
	}


	public void setTjfs(Integer tjfs) {
		this.tjfs = tjfs;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zcid")
	public ClmcEntity getClmc() {
		return clmc;
	}

	public void setClmc(ClmcEntity clmc) {
		this.clmc = clmc;
	}

	public Double getCl() {
		return cl;
	}

	public void setCl(Double cl) {
		this.cl = cl;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
