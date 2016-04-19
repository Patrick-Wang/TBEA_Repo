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

	private static final long serialVersionUID = 1L;
	Integer nf;
	Integer yf;
	Integer dwid;
	Integer type;//		--1 sclx-生产单元   2 sclx-生产类别
	String sclx;
	Double yccnlcz;
	Double yccnlcl;
	Double sykglyddzlcz;
	Double sykglyddzlcl;
	Double dnkglyddzlcz;
	Double dnkglyddzlcl;
	Double nj1yddlcz;
	Double nj1yddlcl;
	Double nj2yddlcz;
	Double nj2yddlcl;
	Double nj3yddlcz;
	Double nj3yddlcl;
	Double nj4yddlcz;
	Double nj4yddlcl;
	Double nj5yddlcz;
	Double nj5yddlcl;
	Double nj6yddlcz;
	Double nj6yddlcl;
	Double nj6yyhkglyddlcz;
	Double nj6yyhkglyddlcl;
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
	public Double getSykglyddzlcz() {
		return sykglyddzlcz;
	}
	public void setSykglyddzlcz(Double sykglyddzlcz) {
		this.sykglyddzlcz = sykglyddzlcz;
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
	public Double getNj1yddlcz() {
		return nj1yddlcz;
	}
	public void setNj1yddlcz(Double nj1yddlcz) {
		this.nj1yddlcz = nj1yddlcz;
	}
	public Double getNj1yddlcl() {
		return nj1yddlcl;
	}
	public void setNj1yddlcl(Double nj1yddlcl) {
		this.nj1yddlcl = nj1yddlcl;
	}
	public Double getNj2yddlcz() {
		return nj2yddlcz;
	}
	public void setNj2yddlcz(Double nj2yddlcz) {
		this.nj2yddlcz = nj2yddlcz;
	}
	public Double getNj2yddlcl() {
		return nj2yddlcl;
	}
	public void setNj2yddlcl(Double nj2yddlcl) {
		this.nj2yddlcl = nj2yddlcl;
	}
	public Double getNj3yddlcz() {
		return nj3yddlcz;
	}
	public void setNj3yddlcz(Double nj3yddlcz) {
		this.nj3yddlcz = nj3yddlcz;
	}
	public Double getNj3yddlcl() {
		return nj3yddlcl;
	}
	public void setNj3yddlcl(Double nj3yddlcl) {
		this.nj3yddlcl = nj3yddlcl;
	}
	public Double getNj4yddlcz() {
		return nj4yddlcz;
	}
	public void setNj4yddlcz(Double nj4yddlcz) {
		this.nj4yddlcz = nj4yddlcz;
	}
	public Double getNj4yddlcl() {
		return nj4yddlcl;
	}
	public void setNj4yddlcl(Double nj4yddlcl) {
		this.nj4yddlcl = nj4yddlcl;
	}
	public Double getNj5yddlcz() {
		return nj5yddlcz;
	}
	public void setNj5yddlcz(Double nj5yddlcz) {
		this.nj5yddlcz = nj5yddlcz;
	}
	public Double getNj5yddlcl() {
		return nj5yddlcl;
	}
	public void setNj5yddlcl(Double nj5yddlcl) {
		this.nj5yddlcl = nj5yddlcl;
	}
	public Double getNj6yddlcz() {
		return nj6yddlcz;
	}
	public void setNj6yddlcz(Double nj6yddlcz) {
		this.nj6yddlcz = nj6yddlcz;
	}
	public Double getNj6yddlcl() {
		return nj6yddlcl;
	}
	public void setNj6yddlcl(Double nj6yddlcl) {
		this.nj6yddlcl = nj6yddlcl;
	}
	public Double getNj6yyhkglyddlcz() {
		return nj6yyhkglyddlcz;
	}
	public void setNj6yyhkglyddlcz(Double nj6yyhkglyddlcz) {
		this.nj6yyhkglyddlcz = nj6yyhkglyddlcz;
	}
	public Double getNj6yyhkglyddlcl() {
		return nj6yyhkglyddlcl;
	}
	public void setNj6yyhkglyddlcl(Double nj6yyhkglyddlcl) {
		this.nj6yyhkglyddlcl = nj6yyhkglyddlcl;
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
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
}
