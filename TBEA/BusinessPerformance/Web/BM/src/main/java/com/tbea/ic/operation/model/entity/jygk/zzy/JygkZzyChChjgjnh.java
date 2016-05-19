package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_ch_chjgjnh database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_ch_chjgjnh")
@NamedQuery(name="JygkZzyChChjgjnh.findAll", query="SELECT j FROM JygkZzyChChjgjnh j")
public class JygkZzyChChjgjnh extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal bcp;
	private int dwid;
	private BigDecimal hj;
	private int nf;
	private BigDecimal qhfdk;
	private BigDecimal qhfdy;
	private BigDecimal qt;
	private BigDecimal sjkc;
	private BigDecimal wkhykp;
	private Timestamp xgsj;
	private BigDecimal ycl;
	private int yf;
	private BigDecimal yfhwkfp;

	public JygkZzyChChjgjnh() {
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


	public BigDecimal getBcp() {
		return this.bcp;
	}

	public void setBcp(BigDecimal bcp) {
		this.bcp = bcp;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public BigDecimal getHj() {
		return this.hj;
	}

	public void setHj(BigDecimal hj) {
		this.hj = hj;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getQhfdk() {
		return this.qhfdk;
	}

	public void setQhfdk(BigDecimal qhfdk) {
		this.qhfdk = qhfdk;
	}


	public BigDecimal getQhfdy() {
		return this.qhfdy;
	}

	public void setQhfdy(BigDecimal qhfdy) {
		this.qhfdy = qhfdy;
	}


	public BigDecimal getQt() {
		return this.qt;
	}

	public void setQt(BigDecimal qt) {
		this.qt = qt;
	}


	public BigDecimal getSjkc() {
		return this.sjkc;
	}

	public void setSjkc(BigDecimal sjkc) {
		this.sjkc = sjkc;
	}


	public BigDecimal getWkhykp() {
		return this.wkhykp;
	}

	public void setWkhykp(BigDecimal wkhykp) {
		this.wkhykp = wkhykp;
	}


	public Timestamp getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}


	public BigDecimal getYcl() {
		return this.ycl;
	}

	public void setYcl(BigDecimal ycl) {
		this.ycl = ycl;
	}


	public int getYf() {
		return this.yf;
	}

	public void setYf(int yf) {
		this.yf = yf;
	}


	public BigDecimal getYfhwkfp() {
		return this.yfhwkfp;
	}

	public void setYfhwkfp(BigDecimal yfhwkfp) {
		this.yfhwkfp = yfhwkfp;
	}

}