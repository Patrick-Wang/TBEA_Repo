package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_jkcb_zbwcqk database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_jkcb_zbwcqk")
@NamedQuery(name="JygkZzyFxJkcbZbwcqk.findAll", query="SELECT j FROM JygkZzyFxJkcbZbwcqk j")
public class JygkZzyFxJkcbZbwcqk extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private int nf;
	private Timestamp xgsj;
	private int yd;
	private BigDecimal zbz;
	private String zzyzbflCode;

	public JygkZzyFxJkcbZbwcqk() {
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


	public Timestamp getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}


	public int getYd() {
		return this.yd;
	}

	public void setYd(int yd) {
		this.yd = yd;
	}


	public BigDecimal getZbz() {
		return this.zbz;
	}

	public void setZbz(BigDecimal zbz) {
		this.zbz = zbz;
	}


	@Column(name="zzyzbfl_code")
	public String getZzyzbflCode() {
		return this.zzyzbflCode;
	}

	public void setZzyzbflCode(String zzyzbflCode) {
		this.zzyzbflCode = zzyzbflCode;
	}

}