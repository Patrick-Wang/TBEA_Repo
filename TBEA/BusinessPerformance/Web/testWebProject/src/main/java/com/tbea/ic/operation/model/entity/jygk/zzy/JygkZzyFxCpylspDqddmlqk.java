package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_fx_cpylsp_dqddmlqk database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fx_cpylsp_dqddmlqk")
@NamedQuery(name="JygkZzyFxCpylspDqddmlqk.findAll", query="SELECT j FROM JygkZzyFxCpylspDqddmlqk j")
public class JygkZzyFxCpylspDqddmlqk extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal mle;
	private int nf;
	private BigDecimal sr;
	private Timestamp xgsj;
	private int yf;
	private String zzyzbflCode;

	public JygkZzyFxCpylspDqddmlqk() {
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


	public BigDecimal getMle() {
		return this.mle;
	}

	public void setMle(BigDecimal mle) {
		this.mle = mle;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getSr() {
		return this.sr;
	}

	public void setSr(BigDecimal sr) {
		this.sr = sr;
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


	@Column(name="zzyzbfl_code")
	public String getZzyzbflCode() {
		return this.zzyzbflCode;
	}

	public void setZzyzbflCode(String zzyzbflCode) {
		this.zzyzbflCode = zzyzbflCode;
	}

}