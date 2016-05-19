package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_cc_ccwcqk_gs database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_sxfykz")
@NamedQuery(name="JygkZzyFxSxfykz.findAll", query="SELECT j FROM JygkZzyFxSxfykz j")
public class JygkZzyFxSxfykz extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal ndjhfyl;
	private int nf;
	private Timestamp xgsj;
	private int yf;
	private int zbxxid;

	public JygkZzyFxSxfykz() {
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


	public BigDecimal getNdjhfyl() {
		return this.ndjhfyl;
	}

	public void setNdjhfyl(BigDecimal ndjhfyl) {
		this.ndjhfyl = ndjhfyl;
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


	public int getYf() {
		return this.yf;
	}

	public void setYf(int yf) {
		this.yf = yf;
	}


	@Column(name="zbxxid")
	public int getZbxxid() {
		return this.zbxxid;
	}

	public void setZbxxid(int zbxxid) {
		this.zbxxid = zbxxid;
	}

}