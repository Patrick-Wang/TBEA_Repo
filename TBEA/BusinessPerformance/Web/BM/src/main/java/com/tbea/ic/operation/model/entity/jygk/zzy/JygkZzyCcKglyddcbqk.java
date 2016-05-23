package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_cc_kglyddcbqk database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_cc_kglyddcbqk")
@NamedQuery(name="JygkZzyCcKglyddcbqk.findAll", query="SELECT j FROM JygkZzyCcKglyddcbqk j")
public class JygkZzyCcKglyddcbqk extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal ddcl;
	private BigDecimal ddcz;
	private int dwid;
	private BigDecimal kglyddzcl;
	private BigDecimal kglyddzcz;
	private BigDecimal n1cl;
	private BigDecimal n1cz;
	private BigDecimal n1czn;
	private BigDecimal n2cl;
	private BigDecimal n2cz;
	private BigDecimal n2czn;
	private BigDecimal n3cl;
	private BigDecimal n3cz;
	private BigDecimal n3czn;
	private BigDecimal n3hcz;
	private BigDecimal n4cl;
	private BigDecimal n4cz;
	private BigDecimal n5cl;
	private BigDecimal n5cz;
	private BigDecimal n6cl;
	private BigDecimal n6cz;
	private BigDecimal n6hcl;
	private BigDecimal n6hcz;
	private BigDecimal ndkglyddzcl;
	private BigDecimal ndkglyddzcz;
	private int nf;
	private BigDecimal wxcn;
	private BigDecimal wxcz;
	private Timestamp xgsj;
	private BigDecimal yccnlcl;
	private BigDecimal yccnlcz;
	private int yf;

	public JygkZzyCcKglyddcbqk() {
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


	public BigDecimal getDdcl() {
		return this.ddcl;
	}

	public void setDdcl(BigDecimal ddcl) {
		this.ddcl = ddcl;
	}


	public BigDecimal getDdcz() {
		return this.ddcz;
	}

	public void setDdcz(BigDecimal ddcz) {
		this.ddcz = ddcz;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public BigDecimal getKglyddzcl() {
		return this.kglyddzcl;
	}

	public void setKglyddzcl(BigDecimal kglyddzcl) {
		this.kglyddzcl = kglyddzcl;
	}


	public BigDecimal getKglyddzcz() {
		return this.kglyddzcz;
	}

	public void setKglyddzcz(BigDecimal kglyddzcz) {
		this.kglyddzcz = kglyddzcz;
	}


	public BigDecimal getN1cl() {
		return this.n1cl;
	}

	public void setN1cl(BigDecimal n1cl) {
		this.n1cl = n1cl;
	}


	public BigDecimal getN1cz() {
		return this.n1cz;
	}

	public void setN1cz(BigDecimal n1cz) {
		this.n1cz = n1cz;
	}


	public BigDecimal getN1czn() {
		return this.n1czn;
	}

	public void setN1czn(BigDecimal n1czn) {
		this.n1czn = n1czn;
	}


	public BigDecimal getN2cl() {
		return this.n2cl;
	}

	public void setN2cl(BigDecimal n2cl) {
		this.n2cl = n2cl;
	}


	public BigDecimal getN2cz() {
		return this.n2cz;
	}

	public void setN2cz(BigDecimal n2cz) {
		this.n2cz = n2cz;
	}


	public BigDecimal getN2czn() {
		return this.n2czn;
	}

	public void setN2czn(BigDecimal n2czn) {
		this.n2czn = n2czn;
	}


	public BigDecimal getN3cl() {
		return this.n3cl;
	}

	public void setN3cl(BigDecimal n3cl) {
		this.n3cl = n3cl;
	}


	public BigDecimal getN3cz() {
		return this.n3cz;
	}

	public void setN3cz(BigDecimal n3cz) {
		this.n3cz = n3cz;
	}


	public BigDecimal getN3czn() {
		return this.n3czn;
	}

	public void setN3czn(BigDecimal n3czn) {
		this.n3czn = n3czn;
	}


	public BigDecimal getN3hcz() {
		return this.n3hcz;
	}

	public void setN3hcz(BigDecimal n3hcz) {
		this.n3hcz = n3hcz;
	}


	public BigDecimal getN4cl() {
		return this.n4cl;
	}

	public void setN4cl(BigDecimal n4cl) {
		this.n4cl = n4cl;
	}


	public BigDecimal getN4cz() {
		return this.n4cz;
	}

	public void setN4cz(BigDecimal n4cz) {
		this.n4cz = n4cz;
	}


	public BigDecimal getN5cl() {
		return this.n5cl;
	}

	public void setN5cl(BigDecimal n5cl) {
		this.n5cl = n5cl;
	}


	public BigDecimal getN5cz() {
		return this.n5cz;
	}

	public void setN5cz(BigDecimal n5cz) {
		this.n5cz = n5cz;
	}


	public BigDecimal getN6cl() {
		return this.n6cl;
	}

	public void setN6cl(BigDecimal n6cl) {
		this.n6cl = n6cl;
	}


	public BigDecimal getN6cz() {
		return this.n6cz;
	}

	public void setN6cz(BigDecimal n6cz) {
		this.n6cz = n6cz;
	}


	public BigDecimal getN6hcl() {
		return this.n6hcl;
	}

	public void setN6hcl(BigDecimal n6hcl) {
		this.n6hcl = n6hcl;
	}


	public BigDecimal getN6hcz() {
		return this.n6hcz;
	}

	public void setN6hcz(BigDecimal n6hcz) {
		this.n6hcz = n6hcz;
	}


	public BigDecimal getNdkglyddzcl() {
		return this.ndkglyddzcl;
	}

	public void setNdkglyddzcl(BigDecimal ndkglyddzcl) {
		this.ndkglyddzcl = ndkglyddzcl;
	}


	public BigDecimal getNdkglyddzcz() {
		return this.ndkglyddzcz;
	}

	public void setNdkglyddzcz(BigDecimal ndkglyddzcz) {
		this.ndkglyddzcz = ndkglyddzcz;
	}


	public int getNf() {
		return this.nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}


	public BigDecimal getWxcn() {
		return this.wxcn;
	}

	public void setWxcn(BigDecimal wxcn) {
		this.wxcn = wxcn;
	}


	public BigDecimal getWxcz() {
		return this.wxcz;
	}

	public void setWxcz(BigDecimal wxcz) {
		this.wxcz = wxcz;
	}


	public Timestamp getXgsj() {
		return this.xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}


	public BigDecimal getYccnlcl() {
		return this.yccnlcl;
	}

	public void setYccnlcl(BigDecimal yccnlcl) {
		this.yccnlcl = yccnlcl;
	}


	public BigDecimal getYccnlcz() {
		return this.yccnlcz;
	}

	public void setYccnlcz(BigDecimal yccnlcz) {
		this.yccnlcz = yccnlcz;
	}


	public int getYf() {
		return this.yf;
	}

	public void setYf(int yf) {
		this.yf = yf;
	}

}