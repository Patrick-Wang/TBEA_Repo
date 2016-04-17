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
	Double wlyddzl;
	Double dnwlyddzl;
	Double nj1yddlypc;
	Double nj1yddlwpc;
	Double nj2yddlypc;
	Double nj2yddlwpc;
	Double nj3yddlypc;
	Double nj3yddlwpc;
	Double nj3yyhlydd;
	Double cnjyhkglyddpcz;
	Double jhqdd;
	Double wx;
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
	public Double getWlyddzl() {
		return wlyddzl;
	}
	public void setWlyddzl(Double wlyddzl) {
		this.wlyddzl = wlyddzl;
	}
	public Double getNj1yddlypc() {
		return nj1yddlypc;
	}
	public void setNj1yddlypc(Double nj1yddlypc) {
		this.nj1yddlypc = nj1yddlypc;
	}
	public Double getNj1yddlwpc() {
		return nj1yddlwpc;
	}
	public void setNj1yddlwpc(Double nj1yddlwpc) {
		this.nj1yddlwpc = nj1yddlwpc;
	}
	public Double getNj2yddlypc() {
		return nj2yddlypc;
	}
	public void setNj2yddlypc(Double nj2yddlypc) {
		this.nj2yddlypc = nj2yddlypc;
	}
	public Double getNj2yddlwpc() {
		return nj2yddlwpc;
	}
	public void setNj2yddlwpc(Double nj2yddlwpc) {
		this.nj2yddlwpc = nj2yddlwpc;
	}
	public Double getNj3yddlypc() {
		return nj3yddlypc;
	}
	public void setNj3yddlypc(Double nj3yddlypc) {
		this.nj3yddlypc = nj3yddlypc;
	}
	public Double getNj3yddlwpc() {
		return nj3yddlwpc;
	}
	public void setNj3yddlwpc(Double nj3yddlwpc) {
		this.nj3yddlwpc = nj3yddlwpc;
	}
	public Double getNj3yyhlydd() {
		return nj3yyhlydd;
	}
	public void setNj3yyhlydd(Double nj3yyhlydd) {
		this.nj3yyhlydd = nj3yyhlydd;
	}
	public Double getCnjyhkglyddpcz() {
		return cnjyhkglyddpcz;
	}
	public void setCnjyhkglyddpcz(Double cnjyhkglyddpcz) {
		this.cnjyhkglyddpcz = cnjyhkglyddpcz;
	}
	public Double getJhqdd() {
		return jhqdd;
	}
	public void setJhqdd(Double jhqdd) {
		this.jhqdd = jhqdd;
	}
	public Double getWx() {
		return wx;
	}
	public void setWx(Double wx) {
		this.wx = wx;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Double getDnwlyddzl() {
		return dnwlyddzl;
	}
	public void setDnwlyddzl(Double dnwlyddzl) {
		this.dnwlyddzl = dnwlyddzl;
	}

}
