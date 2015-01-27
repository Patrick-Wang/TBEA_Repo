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
@Table(name = "cb_zj_xm")
public class XMXX extends AbstractReadWriteEntity {
	Date	gxrq	;
	String	xmbh	;
	String	xmmc	;
	String	ddszdw	;
	String	yhdwmc	;
	String	khhylx	;
	Integer	gb	;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name="[gxrq]")
	public Date getGxrq() {
		return gxrq;
	}

	@Column(name="[xmbh]")
	public String getXmbh() {
		return xmbh;
	}

	@Column(name="[xmmc]")
	public String getXmmc() {
		return xmmc;
	}

	@Column(name="[ddszdw]")
	public String getDdszdw() {
		return ddszdw;
	}

	@Column(name="[yhdwmc]")
	public String getYhdwmc() {
		return yhdwmc;
	}

	@Column(name="[khhylx]")
	public String getKhhylx() {
		return khhylx;
	}

	@Column(name="[gb]")
	public Integer getGb() {
		return gb;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param xmbh the xmbh to set
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	/**
	 * @param xxmc the xxmc to set
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	/**
	 * @param ddszdw the ddszdw to set
	 */
	public void setDdszdw(String ddszdw) {
		this.ddszdw = ddszdw;
	}

	/**
	 * @param yhdwmc the yhdwmc to set
	 */
	public void setYhdwmc(String yhdwmc) {
		this.yhdwmc = yhdwmc;
	}

	/**
	 * @param khhylx the khhylx to set
	 */
	public void setKhhylx(String khhylx) {
		this.khhylx = khhylx;
	}

	/**
	 * @param gb the gb to set
	 */
	public void setGb(Integer gb) {
		this.gb = gb;
	}
}
