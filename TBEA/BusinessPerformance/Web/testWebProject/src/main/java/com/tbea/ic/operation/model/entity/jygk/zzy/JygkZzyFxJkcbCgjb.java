package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_cgjb database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_cgjb")
@NamedQuery(name="JygkZzyFxJkcbCgjb.findAll", query="SELECT j FROM JygkZzyFxJkcbCgjb j")
public class JygkZzyFxJkcbCgjb extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal ndjh;
	private int nf;
	private Timestamp xgsj;
	private BigDecimal ydjh;
	private BigDecimal ydwc;
	private int yf;
	private String zzyzbflCode;

	public JygkZzyFxJkcbCgjb() {
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


	public BigDecimal getNdjh() {
		return this.ndjh;
	}

	public void setNdjh(BigDecimal ndjh) {
		this.ndjh = ndjh;
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


	public BigDecimal getYdjh() {
		return this.ydjh;
	}

	public void setYdjh(BigDecimal ydjh) {
		this.ydjh = ydjh;
	}


	public BigDecimal getYdwc() {
		return this.ydwc;
	}

	public void setYdwc(BigDecimal ydwc) {
		this.ydwc = ydwc;
	}


	public int getYf() {
		return this.yf;
	}

	public void setYf(int yf) {
		this.yf = yf;
	}


	@Column(name="zzyzbfl_code")
	public String getZzyzbflCode() {
		return this.zzyzbflCode;
	}

	public void setZzyzbflCode(String zzyzbflCode) {
		this.zzyzbflCode = zzyzbflCode;
	}

}