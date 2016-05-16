package com.tbea.ic.operation.model.entity.cpzlqkyd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "sbdcpzlqk_zltjjg")
public class TjjgEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer bhgs;
	Integer zs;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
