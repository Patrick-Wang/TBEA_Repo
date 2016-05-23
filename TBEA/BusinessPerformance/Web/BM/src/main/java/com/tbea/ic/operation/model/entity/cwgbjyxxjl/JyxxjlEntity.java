package com.tbea.ic.operation.model.entity.cwgbjyxxjl;

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

import com.tbea.ic.operation.model.entity.identifier.cwgb.KmEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "cwgb_jyxxjl")
public class JyxxjlEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	
	Integer nf;
	Integer yf;
	DWXX dwxx;
	KmEntity km;
	Double jhz;
	Double sjz;
	Double ndlj;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "km")
	public KmEntity getKm() {
		return km;
	}

	public void setKm(KmEntity km) {
		this.km = km;
	}

	public Double getJhz() {
		return jhz;
	}

	public void setJhz(Double jhz) {
		this.jhz = jhz;
	}

	public Double getSjz() {
		return sjz;
	}

	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}

	public Double getNdlj() {
		return ndlj;
	}

	public void setNdlj(Double ndlj) {
		this.ndlj = ndlj;
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
