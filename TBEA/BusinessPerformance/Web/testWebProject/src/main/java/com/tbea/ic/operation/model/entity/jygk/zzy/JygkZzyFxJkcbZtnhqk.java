package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_ztnhqk database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_ztnhqk")
@NamedQuery(name="JygkZzyFxJkcbZtnhqk.findAll", query="SELECT j FROM JygkZzyFxJkcbZtnhqk j")
public class JygkZzyFxJkcbZtnhqk extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal dje;
	private int dwid;
	private BigDecimal dyl;
	private int nf;
	private BigDecimal rqje;
	private BigDecimal rqyl;
	private BigDecimal sje;
	private BigDecimal syl;
	private Timestamp xgsj;
	private int yf;
	private BigDecimal zqje;
	private BigDecimal zqyl;

	public JygkZzyFxJkcbZtnhqk() {
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


	public BigDecimal getDje() {
		return this.dje;
	}

	public void setDje(BigDecimal dje) {
		this.dje = dje;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public BigDecimal getDyl() {
		return this.dyl;
	}

	public void setDyl(BigDecimal dyl) {
		this.dyl = dyl;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getRqje() {
		return this.rqje;
	}

	public void setRqje(BigDecimal rqje) {
		this.rqje = rqje;
	}


	public BigDecimal getRqyl() {
		return this.rqyl;
	}

	public void setRqyl(BigDecimal rqyl) {
		this.rqyl = rqyl;
	}


	public BigDecimal getSje() {
		return this.sje;
	}

	public void setSje(BigDecimal sje) {
		this.sje = sje;
	}


	public BigDecimal getSyl() {
		return this.syl;
	}

	public void setSyl(BigDecimal syl) {
		this.syl = syl;
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


	public BigDecimal getZqje() {
		return this.zqje;
	}

	public void setZqje(BigDecimal zqje) {
		this.zqje = zqje;
	}


	public BigDecimal getZqyl() {
		return this.zqyl;
	}

	public void setZqyl(BigDecimal zqyl) {
		this.zqyl = zqyl;
	}

}