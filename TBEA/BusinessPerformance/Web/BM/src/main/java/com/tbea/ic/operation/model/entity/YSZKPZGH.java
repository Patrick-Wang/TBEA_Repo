package com.tbea.ic.operation.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszk_zj_yszkpzgh")
public class YSZKPZGH  extends AbstractReadWriteEntity{
	
	Date	gxrq	;
	String	yf	;
	String	gsbm	;
	Double	symljxssr	;
	Double	byjhxssr	;
	Double	byysnkzb	;
	Double	symzmysye	;
	Double	byxssrxzysje	;
	Double	bykjyszjhlje	;
	Double	byghblzjysje	;
	Double	byxzblhkcjysje	;
	Double	symykpwfhcsysje	;
	Double	symyfhwkpzjsjysje	;
	Double	symblhkcjysje	;
	Double	symyscjysje	;
	Double	qtcjys	;
	Double	byfhcpxzysje	;
	Double	byhkjdysje	;
	String	sfdrwc	;
	Integer	qybh	;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	/**
	 * @return the gxrq
	 */
	public Date getGxrq() {
		return gxrq;
	}

	/**
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}

	/**
	 * @return the gsbm
	 */
	public String getGsbm() {
		return gsbm;
	}

	/**
	 * @return the symljxssr
	 */
	public Double getSymljxssr() {
		return symljxssr;
	}

	/**
	 * @return the byjhxssr
	 */
	public Double getByjhxssr() {
		return byjhxssr;
	}

	/**
	 * @return the byysnkzb
	 */
	public Double getByysnkzb() {
		return byysnkzb;
	}

	/**
	 * @return the symzmysye
	 */
	public Double getSymzmysye() {
		return symzmysye;
	}

	/**
	 * @return the byxssrxzysje
	 */
	public Double getByxssrxzysje() {
		return byxssrxzysje;
	}

	/**
	 * @return the bykjyszjhlje
	 */
	public Double getBykjyszjhlje() {
		return bykjyszjhlje;
	}

	/**
	 * @return the byghblzjysje
	 */
	public Double getByghblzjysje() {
		return byghblzjysje;
	}

	/**
	 * @return the byxzblhkcjysje
	 */
	public Double getByxzblhkcjysje() {
		return byxzblhkcjysje;
	}

	/**
	 * @return the symykpwfhcsysje
	 */
	public Double getSymykpwfhcsysje() {
		return symykpwfhcsysje;
	}

	/**
	 * @return the symyfhwkpzjsjysje
	 */
	public Double getSymyfhwkpzjsjysje() {
		return symyfhwkpzjsjysje;
	}

	/**
	 * @return the symblhkcjysje
	 */
	public Double getSymblhkcjysje() {
		return symblhkcjysje;
	}

	/**
	 * @return the symyscjysje
	 */
	public Double getSymyscjysje() {
		return symyscjysje;
	}

	/**
	 * @return the qtcjys
	 */
	public Double getQtcjys() {
		return qtcjys;
	}

	/**
	 * @return the byfhcpxzysje
	 */
	public Double getByfhcpxzysje() {
		return byfhcpxzysje;
	}

	/**
	 * @return the byhkjdysje
	 */
	public Double getByhkjdysje() {
		return byhkjdysje;
	}

	/**
	 * @return the sfdrwc
	 */
	public String getSfdrwc() {
		return sfdrwc;
	}

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param yf the yf to set
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}

	/**
	 * @param gsbm the gsbm to set
	 */
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	/**
	 * @param symljxssr the symljxssr to set
	 */
	public void setSymljxssr(Double symljxssr) {
		this.symljxssr = symljxssr;
	}

	/**
	 * @param byjhxssr the byjhxssr to set
	 */
	public void setByjhxssr(Double byjhxssr) {
		this.byjhxssr = byjhxssr;
	}

	/**
	 * @param byysnkzb the byysnkzb to set
	 */
	public void setByysnkzb(Double byysnkzb) {
		this.byysnkzb = byysnkzb;
	}

	/**
	 * @param symzmysye the symzmysye to set
	 */
	public void setSymzmysye(Double symzmysye) {
		this.symzmysye = symzmysye;
	}

	/**
	 * @param byxssrxzysje the byxssrxzysje to set
	 */
	public void setByxssrxzysje(Double byxssrxzysje) {
		this.byxssrxzysje = byxssrxzysje;
	}

	/**
	 * @param bykjyszjhlje the bykjyszjhlje to set
	 */
	public void setBykjyszjhlje(Double bykjyszjhlje) {
		this.bykjyszjhlje = bykjyszjhlje;
	}

	/**
	 * @param byghblzjysje the byghblzjysje to set
	 */
	public void setByghblzjysje(Double byghblzjysje) {
		this.byghblzjysje = byghblzjysje;
	}

	/**
	 * @param byxzblhkcjysje the byxzblhkcjysje to set
	 */
	public void setByxzblhkcjysje(Double byxzblhkcjysje) {
		this.byxzblhkcjysje = byxzblhkcjysje;
	}

	/**
	 * @param symykpwfhcsysje the symykpwfhcsysje to set
	 */
	public void setSymykpwfhcsysje(Double symykpwfhcsysje) {
		this.symykpwfhcsysje = symykpwfhcsysje;
	}

	/**
	 * @param symyfhwkpzjsjysje the symyfhwkpzjsjysje to set
	 */
	public void setSymyfhwkpzjsjysje(Double symyfhwkpzjsjysje) {
		this.symyfhwkpzjsjysje = symyfhwkpzjsjysje;
	}

	/**
	 * @param symblhkcjysje the symblhkcjysje to set
	 */
	public void setSymblhkcjysje(Double symblhkcjysje) {
		this.symblhkcjysje = symblhkcjysje;
	}

	/**
	 * @param symyscjysje the symyscjysje to set
	 */
	public void setSymyscjysje(Double symyscjysje) {
		this.symyscjysje = symyscjysje;
	}

	/**
	 * @param qtcjys the qtcjys to set
	 */
	public void setQtcjys(Double qtcjys) {
		this.qtcjys = qtcjys;
	}

	/**
	 * @param byfhcpxzysje the byfhcpxzysje to set
	 */
	public void setByfhcpxzysje(Double byfhcpxzysje) {
		this.byfhcpxzysje = byfhcpxzysje;
	}

	/**
	 * @param byhkjdysje the byhkjdysje to set
	 */
	public void setByhkjdysje(Double byhkjdysje) {
		this.byhkjdysje = byhkjdysje;
	}

	/**
	 * @param sfdrwc the sfdrwc to set
	 */
	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}
}
