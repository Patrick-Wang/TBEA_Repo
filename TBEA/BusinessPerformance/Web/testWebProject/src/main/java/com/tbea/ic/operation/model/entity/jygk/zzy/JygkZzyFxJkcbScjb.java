package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_scjb database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_scjb")
@NamedQuery(name="JygkZzyFxJkcbScjb.findAll", query="SELECT j FROM JygkZzyFxJkcbScjb j")
public class JygkZzyFxJkcbScjb extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal fl;
	private int nf;
	private BigDecimal sjlyl;
	private Timestamp xgsj;
	private int yf;
	private int zzyflId;

	public JygkZzyFxJkcbScjb() {
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


	public BigDecimal getFl() {
		return this.fl;
	}

	public void setFl(BigDecimal fl) {
		this.fl = fl;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getSjlyl() {
		return this.sjlyl;
	}

	public void setSjlyl(BigDecimal sjlyl) {
		this.sjlyl = sjlyl;
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

	@Column(name="zzyfl_id")
	public int getZzyflId() {
		return this.zzyflId;
	}

	public void setZzyflId(int zzyflId) {
		this.zzyflId = zzyflId;
	}
}