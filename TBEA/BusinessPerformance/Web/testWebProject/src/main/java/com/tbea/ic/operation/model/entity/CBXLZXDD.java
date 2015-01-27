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
@Table(name = "cb_zj_xlzx")
public class CBXLZXDD extends AbstractReadWriteEntity {
	
	Date	gxrq	;
	Integer	tbcpbh	;
	String	hth	;
	Date	htzbsj	;
	String	cpms	;
	Double	xlsl	;
	Integer	ddzxjd	;
	Double	cz	;
	Double	djtyl	;
	Double	djtdj	;
	Double	tjgf	;
	Double	lyl	;
	Double	byqydj	;
	Double	qtcbhj	;
	Double	yf	;

	
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

	@Column(name="[tbcpbh]")
	public Integer getTbcpbh() {
		return tbcpbh;
	}

	@Column(name="[hth]")
	public String getHth() {
		return hth;
	}

	@Column(name="[htzbsj]")
	public Date getHtzbsj() {
		return htzbsj;
	}

	@Column(name="[cpms]")
	public String getCpms() {
		return cpms;
	}

	@Column(name="[xlsl]")
	public Double getXlsl() {
		return xlsl;
	}

	@Column(name="[ddzxjd]")
	public Integer getDdzxjd() {
		return ddzxjd;
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

	@Column(name="[byqydj]")
	public Double getByqydj() {
		return byqydj;
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
	 * @param tbcpbh the tbcpbh to set
	 */
	public void setTbcpbh(Integer tbcpbh) {
		this.tbcpbh = tbcpbh;
	}

	/**
	 * @param hth the hth to set
	 */
	public void setHth(String hth) {
		this.hth = hth;
	}

	/**
	 * @param htzbsj the htzbsj to set
	 */
	public void setHtzbsj(Date htzbsj) {
		this.htzbsj = htzbsj;
	}

	/**
	 * @param cpms the cpms to set
	 */
	public void setCpms(String cpms) {
		this.cpms = cpms;
	}

	/**
	 * @param xlsl the xlsl to set
	 */
	public void setXlsl(Double xlsl) {
		this.xlsl = xlsl;
	}

	/**
	 * @param ddzxjd the ddzxjd to set
	 */
	public void setDdzxjd(Integer ddzxjd) {
		this.ddzxjd = ddzxjd;
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
	public void setByqydj(Double byqydj) {
		this.byqydj = byqydj;
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



}
