package com.tbea.datatransfer.model.entity.zjbyq;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_yszkpzgh")
public class YSZKPZGHBYQ extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String yf;

	private String gsbm;

	private Double symljxssr;

	private Double byjhxssr;

	private Double byysnkzb;

	private Double symzmysye;

	private Double byxssrxzysje;

	private Double bykjyszjhlje;

	private Double byghblzjysje;

	private Double byxzblhkcjysje;

	private Double symykpwfhcsysje;

	private Double symyfhwkpzjsjysje;

	private Double symblhkcjysje;

	private Double symyscjysje;

	private Double qtcjys;

	private Double byfhcpxzysje;

	private Double byhkjdysje;

	private String sfdrwc;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Date getGxrq() {
		return gxrq;
	}

	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public Double getSymljxssr() {
		return symljxssr;
	}

	public void setSymljxssr(Double symljxssr) {
		this.symljxssr = symljxssr;
	}

	public Double getByjhxssr() {
		return byjhxssr;
	}

	public void setByjhxssr(Double byjhxssr) {
		this.byjhxssr = byjhxssr;
	}

	public Double getByysnkzb() {
		return byysnkzb;
	}

	public void setByysnkzb(Double byysnkzb) {
		this.byysnkzb = byysnkzb;
	}

	public Double getSymzmysye() {
		return symzmysye;
	}

	public void setSymzmysye(Double symzmysye) {
		this.symzmysye = symzmysye;
	}

	public Double getByxssrxzysje() {
		return byxssrxzysje;
	}

	public void setByxssrxzysje(Double byxssrxzysje) {
		this.byxssrxzysje = byxssrxzysje;
	}

	public Double getBykjyszjhlje() {
		return bykjyszjhlje;
	}

	public void setBykjyszjhlje(Double bykjyszjhlje) {
		this.bykjyszjhlje = bykjyszjhlje;
	}

	public Double getByghblzjysje() {
		return byghblzjysje;
	}

	public void setByghblzjysje(Double byghblzjysje) {
		this.byghblzjysje = byghblzjysje;
	}

	public Double getByxzblhkcjysje() {
		return byxzblhkcjysje;
	}

	public void setByxzblhkcjysje(Double byxzblhkcjysje) {
		this.byxzblhkcjysje = byxzblhkcjysje;
	}

	public Double getSymykpwfhcsysje() {
		return symykpwfhcsysje;
	}

	public void setSymykpwfhcsysje(Double symykpwfhcsysje) {
		this.symykpwfhcsysje = symykpwfhcsysje;
	}

	public Double getSymyfhwkpzjsjysje() {
		return symyfhwkpzjsjysje;
	}

	public void setSymyfhwkpzjsjysje(Double symyfhwkpzjsjysje) {
		this.symyfhwkpzjsjysje = symyfhwkpzjsjysje;
	}

	public Double getSymblhkcjysje() {
		return symblhkcjysje;
	}

	public void setSymblhkcjysje(Double symblhkcjysje) {
		this.symblhkcjysje = symblhkcjysje;
	}

	public Double getSymyscjysje() {
		return symyscjysje;
	}

	public void setSymyscjysje(Double symyscjysje) {
		this.symyscjysje = symyscjysje;
	}

	public Double getQtcjys() {
		return qtcjys;
	}

	public void setQtcjys(Double qtcjys) {
		this.qtcjys = qtcjys;
	}

	public Double getByfhcpxzysje() {
		return byfhcpxzysje;
	}

	public void setByfhcpxzysje(Double byfhcpxzysje) {
		this.byfhcpxzysje = byfhcpxzysje;
	}

	public Double getByhkjdysje() {
		return byhkjdysje;
	}

	public void setByhkjdysje(Double byhkjdysje) {
		this.byhkjdysje = byhkjdysje;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "YSZKPZGHLocal [id=" + getId() + ", gxrq=" + gxrq + ", yf=" + yf
				+ ", gsbm=" + gsbm + ", symljxssr=" + symljxssr + ", byjhxssr="
				+ byjhxssr + ", byysnkzb=" + byysnkzb + ", symzmysye="
				+ symzmysye + ", byxssrxzysje=" + byxssrxzysje
				+ ", bykjyszjhlje=" + bykjyszjhlje + ", byghblzjysje="
				+ byghblzjysje + ", byxzblhkcjysje=" + byxzblhkcjysje
				+ ", symykpwfhcsysje=" + symykpwfhcsysje
				+ ", symyfhwkpzjsjysje=" + symyfhwkpzjsjysje
				+ ", symblhkcjysje=" + symblhkcjysje + ", symyscjysje="
				+ symyscjysje + ", qtcjys=" + qtcjys + ", byfhcpxzysje="
				+ byfhcpxzysje + ", byhkjdysje=" + byhkjdysje + ", sfdrwc="
				+ sfdrwc + "]";
	}

}
