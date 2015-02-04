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
@Table(name = "cb_zj_xlwg")
public class CBXLWGDD extends AbstractReadWriteEntity {
	

	Date	gxrq	;
	Integer	zxcpbh	;
	String	wgsj	;
	Double	cz	;
	Double	djtyl	;
	Double	djtdj	;
	Double	tjgf	;
	Double	lyl	;
	Double	sjlvdj	;
	Double	qtcbhj	;
	Double	yf	;
	String dwmc;
	Integer qybh;
	
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

	@Column(name="[zxcpbh]")
	public Integer getZxcpbh() {
		return zxcpbh;
	}

	@Column(name="[wgsj]")
	public String getWgsj() {
		return wgsj;
	}

	@Column(name="[cz]")
	public Double getCz() {
		return cz;
	}

	@Column(name="[djtyl]")
	public Double getDjtyl() {
		return djtyl;
	}

	@Column(name="[djtdj]")
	public Double getDjtdj() {
		return djtdj;
	}

	@Column(name="[tjgf]")
	public Double getTjgf() {
		return tjgf;
	}

	@Column(name="[lyl]")
	public Double getLyl() {
		return lyl;
	}

	@Column(name="[sjlvdj]")
	public Double getSjlvdj() {
		return sjlvdj;
	}

	@Column(name="[qtcbhj]")
	public Double getQtcbhj() {
		return qtcbhj;
	}

	@Column(name="[yf]")
	public Double getYf() {
		return yf;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param zxcpbh the zxcpbh to set
	 */
	public void setZxcpbh(Integer zxcpbh) {
		this.zxcpbh = zxcpbh;
	}

	/**
	 * @param wgsj the wgsj to set
	 */
	public void setWgsj(String wgsj) {
		this.wgsj = wgsj;
	}

	/**
	 * @param cz the cz to set
	 */
	public void setCz(Double cz) {
		this.cz = cz;
	}

	/**
	 * @param djtyl the djtyl to set
	 */
	public void setDjtyl(Double djtyl) {
		this.djtyl = djtyl;
	}

	/**
	 * @param djtdj the djtdj to set
	 */
	public void setDjtdj(Double djtdj) {
		this.djtdj = djtdj;
	}

	/**
	 * @param tjgf the tjgf to set
	 */
	public void setTjgf(Double tjgf) {
		this.tjgf = tjgf;
	}

	/**
	 * @param lyl the lyl to set
	 */
	public void setLyl(Double lyl) {
		this.lyl = lyl;
	}

	/**
	 * @param byqydj the byqydj to set
	 */
	public void setSjlvdj(Double sjlvdj) {
		this.sjlvdj = sjlvdj;
	}

	/**
	 * @param qtcbhj the qtcbhj to set
	 */
	public void setQtcbhj(Double qtcbhj) {
		this.qtcbhj = qtcbhj;
	}

	/**
	 * @param yf the yf to set
	 */
	public void setYf(Double yf) {
		this.yf = yf;
	}

	@Column(name="[dwmc]")
	public String getDwmc() {
		return dwmc;
	}

	/**
	 * @param dwmc the dwmc to set
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	

}
