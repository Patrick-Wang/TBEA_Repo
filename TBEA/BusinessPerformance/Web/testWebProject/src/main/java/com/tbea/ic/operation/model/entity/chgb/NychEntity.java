package com.tbea.ic.operation.model.entity.chgb;

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
@Table(name = "chgb_nych")
public class NychEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	Integer nf;
	Integer yf;
	DWXX dwxx;
	Double ycl;	
	Double yl;
	Double bpbj;
	Double kcsp;
	Double sccbDpbtf;
	Double fcsp;
	Double dh;
	
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

	public Double getYcl() {
		return ycl;
	}

	public void setYcl(Double ycl) {
		this.ycl = ycl;
	}

	public Double getYl() {
		return yl;
	}

	public void setYl(Double yl) {
		this.yl = yl;
	}

	public Double getBpbj() {
		return bpbj;
	}

	public void setBpbj(Double bpbj) {
		this.bpbj = bpbj;
	}

	public Double getKcsp() {
		return kcsp;
	}

	public void setKcsp(Double kcsp) {
		this.kcsp = kcsp;
	}

	public Double getSccbDpbtf() {
		return sccbDpbtf;
	}

	public void setSccbDpbtf(Double sccbDpbtf) {
		this.sccbDpbtf = sccbDpbtf;
	}

	public Double getFcsp() {
		return fcsp;
	}

	public void setFcsp(Double fcsp) {
		this.fcsp = fcsp;
	}

	public Double getDh() {
		return dh;
	}

	public void setDh(Double dh) {
		this.dh = dh;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
