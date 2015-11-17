package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_ch_yclch database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_ch_yclch")
@NamedQuery(name="JygkZzyChYclch.findAll", query="SELECT j FROM JygkZzyChYclch j")
public class JygkZzyChYclch extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal jzydkcje;
	private BigDecimal nckcje;
	private int nf;
	private Timestamp xgsj;
	private int yf;
	private int zzyflId;

	public JygkZzyChYclch() {
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


	public BigDecimal getJzydkcje() {
		return this.jzydkcje;
	}

	public void setJzydkcje(BigDecimal jzydkcje) {
		this.jzydkcje = jzydkcje;
	}


	public BigDecimal getNckcje() {
		return this.nckcje;
	}

	public void setNckcje(BigDecimal nckcje) {
		this.nckcje = nckcje;
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


	@Column(name="zzyfl_id")
	public int getZzyflId() {
		return this.zzyflId;
	}

	public void setZzyflId(int zzyflId) {
		this.zzyflId = zzyflId;
	}

}