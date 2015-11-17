package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_cpylsp_hqlyddzl database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_cpylsp_hqlyddzl")
@NamedQuery(name="JygkZzyFxCpylspHqlyddzl.findAll", query="SELECT j FROM JygkZzyFxCpylspHqlyddzl j")
public class JygkZzyFxCpylspHqlyddzl extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal cl;
	private BigDecimal cz;
	private int dwid;
	private int jd;
	private int nf;
	private Timestamp xgsj;
	private BigDecimal yjyhhmle;
	private BigDecimal yjyhhmll;
	private BigDecimal zbmll;
	private int zzyflId;

	public JygkZzyFxCpylspHqlyddzl() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public BigDecimal getCl() {
		return this.cl;
	}

	public void setCl(BigDecimal cl) {
		this.cl = cl;
	}


	public BigDecimal getCz() {
		return this.cz;
	}

	public void setCz(BigDecimal cz) {
		this.cz = cz;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public int getJd() {
		return this.jd;
	}

	public void setJd(int jd) {
		this.jd = jd;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public Timestamp getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}


	public BigDecimal getYjyhhmle() {
		return this.yjyhhmle;
	}

	public void setYjyhhmle(BigDecimal yjyhhmle) {
		this.yjyhhmle = yjyhhmle;
	}


	public BigDecimal getYjyhhmll() {
		return this.yjyhhmll;
	}

	public void setYjyhhmll(BigDecimal yjyhhmll) {
		this.yjyhhmll = yjyhhmll;
	}


	public BigDecimal getZbmll() {
		return this.zbmll;
	}

	public void setZbmll(BigDecimal zbmll) {
		this.zbmll = zbmll;
	}


	@Column(name="zzyfl_id")
	public int getZzyflId() {
		return this.zzyflId;
	}

	public void setZzyflId(int zzyflId) {
		this.zzyflId = zzyflId;
	}
}