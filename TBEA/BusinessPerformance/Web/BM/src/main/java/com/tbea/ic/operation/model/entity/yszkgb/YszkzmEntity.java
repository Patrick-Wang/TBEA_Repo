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


@Entity
@Table(name = "yszkgb_yszkzm")
public class YszkzmEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	Integer nf;
	Integer yf;
	DWXX dwxx;
	Double zmje;
	Double hzzb;
	Double yz;

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

	public Double getZmje() {
		return zmje;
	}

	public void setZmje(Double zmje) {
		this.zmje = zmje;
	}

	public Double getHzzb() {
		return hzzb;
	}

	public void setHzzb(Double hzzb) {
		this.hzzb = hzzb;
	}

	public Double getYz() {
		return yz;
	}

	public void setYz(Double yz) {
		this.yz = yz;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
