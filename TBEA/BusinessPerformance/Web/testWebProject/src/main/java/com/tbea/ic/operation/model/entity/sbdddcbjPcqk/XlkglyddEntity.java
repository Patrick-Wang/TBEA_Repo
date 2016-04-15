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
@Table(name = "sbdddcbjpcqk_xlkglydd")
public class XlkglyddEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer type;	//	--1 sclx-生产单元   2 sclx-生产类别
	String sclx;
	Double yccnl;
	Double sykglyddzlcz;
	Double dnkglyddzlcz;
	Double djdpcddcz;
	Double xjdpcddcz;
	Double cnjyhkglyddpcz;
	Double jhqddcz;
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
	public Double getYccnl() {
		return yccnl;
	}
	public void setYccnl(Double yccnl) {
		this.yccnl = yccnl;
	}
	public Double getSykglyddzlcz() {
		return sykglyddzlcz;
	}
	public void setSykglyddzlcz(Double sykglyddzlcz) {
		this.sykglyddzlcz = sykglyddzlcz;
	}
	public Double getDnkglyddzlcz() {
		return dnkglyddzlcz;
	}
	public void setDnkglyddzlcz(Double dnkglyddzlcz) {
		this.dnkglyddzlcz = dnkglyddzlcz;
	}
	public Double getDjdpcddcz() {
		return djdpcddcz;
	}
	public void setDjdpcddcz(Double djdpcddcz) {
		this.djdpcddcz = djdpcddcz;
	}
	
	public Double getXjdpcddcz() {
		return xjdpcddcz;
	}
	public void setXjdpcddcz(Double xjdpcddcz) {
		this.xjdpcddcz = xjdpcddcz;
	}
	
	public Double getCnjyhkglyddpcz() {
		return cnjyhkglyddpcz;
	}
	public void setCnjyhkglyddpcz(Double cnjyhkglyddpcz) {
		this.cnjyhkglyddpcz = cnjyhkglyddpcz;
	}
	public Double getJhqddcz() {
		return jhqddcz;
	}
	public void setJhqddcz(Double jhqddcz) {
		this.jhqddcz = jhqddcz;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	

}
