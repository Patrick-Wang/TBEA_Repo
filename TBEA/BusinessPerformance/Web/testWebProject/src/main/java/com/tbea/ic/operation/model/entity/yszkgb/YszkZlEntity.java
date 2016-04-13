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
@Table(name = "yszkgb_yszkzl")
public class YszkZlEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double zl5nys;
	Double zl4z5n;
	Double zl3z4n;
	Double zl2z3n;
	Double zl1z2n;
	Double zl1nyn;
	Double hj;
	public Double getHj() {
		return hj;
	}
	public void setHj(Double hj) {
		this.hj = hj;
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
	public Double getZl5nys() {
		return zl5nys;
	}
	public void setZl5nys(Double zl5nys) {
		this.zl5nys = zl5nys;
	}
	public Double getZl4z5n() {
		return zl4z5n;
	}
	public void setZl4z5n(Double zl4z5n) {
		this.zl4z5n = zl4z5n;
	}
	public Double getZl3z4n() {
		return zl3z4n;
	}
	public void setZl3z4n(Double zl3z4n) {
		this.zl3z4n = zl3z4n;
	}
	public Double getZl2z3n() {
		return zl2z3n;
	}
	public void setZl2z3n(Double zl2z3n) {
		this.zl2z3n = zl2z3n;
	}
	public Double getZl1z2n() {
		return zl1z2n;
	}
	public void setZl1z2n(Double zl1z2n) {
		this.zl1z2n = zl1z2n;
	}
	public Double getZl1nyn() {
		return zl1nyn;
	}
	public void setZl1nyn(Double zl1nyn) {
		this.zl1nyn = zl1nyn;
	}

}
