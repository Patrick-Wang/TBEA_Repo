package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_xsjb database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_xsjb")
@NamedQuery(name="JygkZzyFxJkcbXsjb.findAll", query="SELECT j FROM JygkZzyFxJkcbXsjb j")
public class JygkZzyFxJkcbXsjb extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private int nf;
	private BigDecimal qt;
	private BigDecimal qxkhzd;
	private BigDecimal tc;
	private BigDecimal tpzbz;
	private Timestamp xgsj;
	private int yf;
	private BigDecimal yhfkfs;

	public JygkZzyFxJkcbXsjb() {
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


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getQt() {
		return this.qt;
	}

	public void setQt(BigDecimal qt) {
		this.qt = qt;
	}


	public BigDecimal getQxkhzd() {
		return this.qxkhzd;
	}

	public void setQxkhzd(BigDecimal qxkhzd) {
		this.qxkhzd = qxkhzd;
	}


	public BigDecimal getTc() {
		return this.tc;
	}

	public void setTc(BigDecimal tc) {
		this.tc = tc;
	}


	public BigDecimal getTpzbz() {
		return this.tpzbz;
	}

	public void setTpzbz(BigDecimal tpzbz) {
		this.tpzbz = tpzbz;
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


	public BigDecimal getYhfkfs() {
		return this.yhfkfs;
	}

	public void setYhfkfs(BigDecimal yhfkfs) {
		this.yhfkfs = yhfkfs;
	}

}