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
@Table(name = "cb_zj_xltb")
public class CBXLTBDD extends AbstractReadWriteEntity {
	
	Date	gxrq	;
	String	xmbh	;
	Date	tbbjsj	;
	Integer	cpdl	;
	Double	xlsl	;
	Double	cz	;
	String	yjkbsj	;
	Double	yczbgl	;
	Double	djtyl	 = 0.0;
	Double	djtdj	 = 0.0;
	Double	lyl	 = 0.0;
	Double	ldj	 = 0.0;
	Double	qtcbhj	;
	Double	yf	;
	Integer Qybh;

	
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

	@Column(name="[tbbjsj]")
	public Date getTbbjsj() {
		return tbbjsj;
	}

	@Column(name="[cpdl]")
	public Integer getCpdl() {
		return cpdl;
	}

	@Column(name="[xlsl]")
	public Double getXlsl() {
		return xlsl;
	}

	@Column(name="[cz]")
	public Double getCz() {
		return cz;
	}

	@Column(name="[yjkbsj]")
	public String getYjkbsj() {
		return yjkbsj;
	}

	@Column(name="[yczbgl]")
	public Double getYczbgl() {
		return yczbgl;
	}

	@Column(name="[djtyl]")
	public Double getDjtyl() {
		return djtyl;
	}

	@Column(name="[djtdj]")
	public Double getDjtdj() {
		return djtdj;
	}

	@Column(name="[lyl]")
	public Double getLyl() {
		return lyl;
	}

	@Column(name="[tblvdj]")
	public Double getLdj() {
		return ldj;
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
	 * @param xmbh the xmbh to set
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	/**
	 * @param tbbjsj the tbbjsj to set
	 */
	public void setTbbjsj(Date tbbjsj) {
		this.tbbjsj = tbbjsj;
	}

	/**
	 * @param cpdl the cpdl to set
	 */
	public void setCpdl(Integer cpdl) {
		this.cpdl = cpdl;
	}

	/**
	 * @param xlsl the xlsl to set
	 */
	public void setXlsl(Double xlsl) {
		this.xlsl = xlsl;
	}

	/**
	 * @param cz the cz to set
	 */
	public void setCz(Double cz) {
		this.cz = cz;
	}

	/**
	 * @param yjkbsj the yjkbsj to set
	 */
	public void setYjkbsj(String yjkbsj) {
		this.yjkbsj = yjkbsj;
	}

	/**
	 * @param yczbgl the yczbgl to set
	 */
	public void setYczbgl(Double yczbgl) {
		this.yczbgl = yczbgl;
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
	 * @param lyl the lyl to set
	 */
	public void setLyl(Double lyl) {
		this.lyl = lyl;
	}

	/**
	 * @param byqydj the byqydj to set
	 */
	public void setLdj(Double byqydj) {
		this.ldj = byqydj;
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

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return Qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		Qybh = qybh;
	}
	
}
