package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_jsjb database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_jsjb")
@NamedQuery(name="JygkZzyFxJkcbJsjb.findAll", query="SELECT j FROM JygkZzyFxJkcbJsjb j")
public class JygkZzyFxJkcbJsjb extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal cltdjb;
	private int dwid;
	private BigDecimal jgcsyhjb;
	private int nf;
	private BigDecimal qtjb;
	private BigDecimal scts;
	private BigDecimal cz;
	private Timestamp xgsj;
	private int yf;
	private BigDecimal yhts;
	private int zzyflId;

	public JygkZzyFxJkcbJsjb() {
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


	public BigDecimal getCltdjb() {
		return this.cltdjb;
	}

	public void setCltdjb(BigDecimal cltdjb) {
		this.cltdjb = cltdjb;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public BigDecimal getJgcsyhjb() {
		return this.jgcsyhjb;
	}

	public void setJgcsyhjb(BigDecimal jgcsyhjb) {
		this.jgcsyhjb = jgcsyhjb;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getQtjb() {
		return this.qtjb;
	}

	public void setQtjb(BigDecimal qtjb) {
		this.qtjb = qtjb;
	}


	public BigDecimal getScts() {
		return this.scts;
	}

	public void setScts(BigDecimal scts) {
		this.scts = scts;
	}


	public Timestamp getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}


	public int getYf() {
		return this.yf;
	}

	public void setYf(int yf) {
		this.yf = yf;
	}


	public BigDecimal getYhts() {
		return this.yhts;
	}

	public void setYhts(BigDecimal yhts) {
		this.yhts = yhts;
	}


	@Column(name="zzyfl_id")
	public int getZzyflId() {
		return this.zzyflId;
	}

	public void setZzyflId(int zzyflId) {
		this.zzyflId = zzyflId;
	}


	public BigDecimal getCz() {
		return cz;
	}


	public void setCz(BigDecimal cz) {
		this.cz = cz;
	}
}