package com.tbea.ic.operation.model.entity.sbdscqyqk;

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

import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "scfxgb_xfcpqy")
public class XfcpqyEntity extends AbstractReadWriteEntity implements Serializable {
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
	DWXX dwxx;
	Integer tjfs;
	CpmcEntity cpmc;
	Double qye;
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
	public CpmcEntity getCpmc() {
		return cpmc;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpid")
	public void setCpmc(CpmcEntity cpmc) {
		this.cpmc = cpmc;
	}
	public Double getQye() {
		return qye;
	}
	public void setQye(Double qye) {
		this.qye = qye;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}

	
	
}
