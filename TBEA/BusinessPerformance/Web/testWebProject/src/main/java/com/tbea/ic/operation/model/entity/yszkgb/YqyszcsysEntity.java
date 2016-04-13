package com.tbea.ic.operation.model.entity.yszkgb;

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
import com.tbea.ic.operation.model.entity.jygk.SHZT;


@Entity
@Table(name = "yszkgb_yqyszcsys")
public class YqyszcsysEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double nbys;
	Double khzx;
	Double gdfk;
	Double xmbh;
	Double htys;
	Double sxbl;
	Double ss;
	SHZT zt;
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
	public Double getNbys() {
		return nbys;
	}
	public void setNbys(Double nbys) {
		this.nbys = nbys;
	}
	public Double getKhzx() {
		return khzx;
	}
	public void setKhzx(Double khzx) {
		this.khzx = khzx;
	}
	public Double getGdfk() {
		return gdfk;
	}
	public void setGdfk(Double gdfk) {
		this.gdfk = gdfk;
	}
	public Double getXmbh() {
		return xmbh;
	}
	public void setXmbh(Double xmbh) {
		this.xmbh = xmbh;
	}
	public Double getHtys() {
		return htys;
	}
	public void setHtys(Double htys) {
		this.htys = htys;
	}
	public Double getSxbl() {
		return sxbl;
	}
	public void setSxbl(Double sxbl) {
		this.sxbl = sxbl;
	}
	public Double getSs() {
		return ss;
	}
	public void setSs(Double ss) {
		this.ss = ss;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zt")
	public SHZT getZt() {
		return zt;
	}
	public void setZt(SHZT zt) {
		this.zt = zt;
	}

}
