package com.tbea.ic.operation.model.entity.sbdddcbjpcqk;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "sbdddcbjpcqk_byqkglydd")
public class ByqkglyddEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer type;//		--1 sclx-生产单元   2 sclx-生产类别
	String sclx;
	Double yccnlcz;
	Double yccnlcl;
	Double skglyddzlcz;
	Double sykglyddzlcl;
	Double dnkglyddzlcz;
	Double dnkglyddzlcl;
	Double djdpcddcz;
	Double djdpcddcl;
	Double xjdpcddcz;
	Double xjdpcddcl;
	Double cnjyhkglyddcz;
	Double cnjyhkglyddcl;
	Double jhqddcz;
	Double jhqddcl;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSclx() {
		return sclx;
	}
	public void setSclx(String sclx) {
		this.sclx = sclx;
	}
	public Double getYccnlcz() {
		return yccnlcz;
	}
	public void setYccnlcz(Double yccnlcz) {
		this.yccnlcz = yccnlcz;
	}
	public Double getYccnlcl() {
		return yccnlcl;
	}
	public void setYccnlcl(Double yccnlcl) {
		this.yccnlcl = yccnlcl;
	}
	public Double getSkglyddzlcz() {
		return skglyddzlcz;
	}
	public void setSkglyddzlcz(Double skglyddzlcz) {
		this.skglyddzlcz = skglyddzlcz;
	}
	public Double getSykglyddzlcl() {
		return sykglyddzlcl;
	}
	public void setSykglyddzlcl(Double sykglyddzlcl) {
		this.sykglyddzlcl = sykglyddzlcl;
	}
	public Double getDnkglyddzlcz() {
		return dnkglyddzlcz;
	}
	public void setDnkglyddzlcz(Double dnkglyddzlcz) {
		this.dnkglyddzlcz = dnkglyddzlcz;
	}
	public Double getDnkglyddzlcl() {
		return dnkglyddzlcl;
	}
	public void setDnkglyddzlcl(Double dnkglyddzlcl) {
		this.dnkglyddzlcl = dnkglyddzlcl;
	}
	public Double getDjdpcddcz() {
		return djdpcddcz;
	}
	public void setDjdpcddcz(Double djdpcddcz) {
		this.djdpcddcz = djdpcddcz;
	}
	public Double getDjdpcddcl() {
		return djdpcddcl;
	}
	public void setDjdpcddcl(Double djdpcddcl) {
		this.djdpcddcl = djdpcddcl;
	}

	public Double getXjdpcddcz() {
		return xjdpcddcz;
	}
	public void setXjdpcddcz(Double xjdpcddcz) {
		this.xjdpcddcz = xjdpcddcz;
	}
	public Double getXjdpcddcl() {
		return xjdpcddcl;
	}
	public void setXjdpcddcl(Double xjdpcddcl) {
		this.xjdpcddcl = xjdpcddcl;
	}

	public Double getCnjyhkglyddcz() {
		return cnjyhkglyddcz;
	}
	public void setCnjyhkglyddcz(Double cnjyhkglyddcz) {
		this.cnjyhkglyddcz = cnjyhkglyddcz;
	}
	public Double getCnjyhkglyddcl() {
		return cnjyhkglyddcl;
	}
	public void setCnjyhkglyddcl(Double cnjyhkglyddcl) {
		this.cnjyhkglyddcl = cnjyhkglyddcl;
	}
	public Double getJhqddcz() {
		return jhqddcz;
	}
	public void setJhqddcz(Double jhqddcz) {
		this.jhqddcz = jhqddcz;
	}
	public Double getJhqddcl() {
		return jhqddcl;
	}
	public void setJhqddcl(Double jhqddcl) {
		this.jhqddcl = jhqddcl;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
}
