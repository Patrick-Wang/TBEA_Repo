package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the jygk_zzy_ch_zljj database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_ch_zljj")
@NamedQuery(name="JygkZzyChZljj.findAll", query="SELECT j FROM JygkZzyChZljj j")
public class JygkZzyChZljj extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int dwid;
	private BigDecimal hj;
	private BigDecimal n1bcp;
	private BigDecimal n1ccp;
	private BigDecimal n1qt;
	private BigDecimal n1ycl;
	private BigDecimal n1z2bcp;
	private BigDecimal n1z2ccp;
	private BigDecimal n1z2qt;
	private BigDecimal n1z2ycl;
	private BigDecimal n2z3bcp;
	private BigDecimal n2z3ccp;
	private BigDecimal n2z3qt;
	private BigDecimal n2z3ycl;
	private BigDecimal n3z4bcp;
	private BigDecimal n3z4ccp;
	private BigDecimal n3z4qt;
	private BigDecimal n3z4ycl;
	private BigDecimal n4z5bcp;
	private BigDecimal n4z5ccp;
	private BigDecimal n4z5qt;
	private BigDecimal n4z5ycl;
	private BigDecimal n5sbcp;
	private BigDecimal n5sccp;
	private BigDecimal n5sqt;
	private BigDecimal n5sycl;
	private int nf;
	private Timestamp xgsj;
	private int yf;

	public JygkZzyChZljj() {
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


	public BigDecimal getHj() {
		return this.hj;
	}

	public void setHj(BigDecimal hj) {
		this.hj = hj;
	}


	public BigDecimal getN1bcp() {
		return this.n1bcp;
	}

	public void setN1bcp(BigDecimal n1bcp) {
		this.n1bcp = n1bcp;
	}


	public BigDecimal getN1ccp() {
		return this.n1ccp;
	}

	public void setN1ccp(BigDecimal n1ccp) {
		this.n1ccp = n1ccp;
	}


	public BigDecimal getN1qt() {
		return this.n1qt;
	}

	public void setN1qt(BigDecimal n1qt) {
		this.n1qt = n1qt;
	}


	public BigDecimal getN1ycl() {
		return this.n1ycl;
	}

	public void setN1ycl(BigDecimal n1ycl) {
		this.n1ycl = n1ycl;
	}


	public BigDecimal getN1z2bcp() {
		return this.n1z2bcp;
	}

	public void setN1z2bcp(BigDecimal n1z2bcp) {
		this.n1z2bcp = n1z2bcp;
	}


	public BigDecimal getN1z2ccp() {
		return this.n1z2ccp;
	}

	public void setN1z2ccp(BigDecimal n1z2ccp) {
		this.n1z2ccp = n1z2ccp;
	}


	public BigDecimal getN1z2qt() {
		return this.n1z2qt;
	}

	public void setN1z2qt(BigDecimal n1z2qt) {
		this.n1z2qt = n1z2qt;
	}


	public BigDecimal getN1z2ycl() {
		return this.n1z2ycl;
	}

	public void setN1z2ycl(BigDecimal n1z2ycl) {
		this.n1z2ycl = n1z2ycl;
	}


	public BigDecimal getN2z3bcp() {
		return this.n2z3bcp;
	}

	public void setN2z3bcp(BigDecimal n2z3bcp) {
		this.n2z3bcp = n2z3bcp;
	}


	public BigDecimal getN2z3ccp() {
		return this.n2z3ccp;
	}

	public void setN2z3ccp(BigDecimal n2z3ccp) {
		this.n2z3ccp = n2z3ccp;
	}


	public BigDecimal getN2z3qt() {
		return this.n2z3qt;
	}

	public void setN2z3qt(BigDecimal n2z3qt) {
		this.n2z3qt = n2z3qt;
	}


	public BigDecimal getN2z3ycl() {
		return this.n2z3ycl;
	}

	public void setN2z3ycl(BigDecimal n2z3ycl) {
		this.n2z3ycl = n2z3ycl;
	}


	public BigDecimal getN3z4bcp() {
		return this.n3z4bcp;
	}

	public void setN3z4bcp(BigDecimal n3z4bcp) {
		this.n3z4bcp = n3z4bcp;
	}


	public BigDecimal getN3z4ccp() {
		return this.n3z4ccp;
	}

	public void setN3z4ccp(BigDecimal n3z4ccp) {
		this.n3z4ccp = n3z4ccp;
	}


	public BigDecimal getN3z4qt() {
		return this.n3z4qt;
	}

	public void setN3z4qt(BigDecimal n3z4qt) {
		this.n3z4qt = n3z4qt;
	}


	public BigDecimal getN3z4ycl() {
		return this.n3z4ycl;
	}

	public void setN3z4ycl(BigDecimal n3z4ycl) {
		this.n3z4ycl = n3z4ycl;
	}


	public BigDecimal getN4z5bcp() {
		return this.n4z5bcp;
	}

	public void setN4z5bcp(BigDecimal n4z5bcp) {
		this.n4z5bcp = n4z5bcp;
	}


	public BigDecimal getN4z5ccp() {
		return this.n4z5ccp;
	}

	public void setN4z5ccp(BigDecimal n4z5ccp) {
		this.n4z5ccp = n4z5ccp;
	}


	public BigDecimal getN4z5qt() {
		return this.n4z5qt;
	}

	public void setN4z5qt(BigDecimal n4z5qt) {
		this.n4z5qt = n4z5qt;
	}


	public BigDecimal getN4z5ycl() {
		return this.n4z5ycl;
	}

	public void setN4z5ycl(BigDecimal n4z5ycl) {
		this.n4z5ycl = n4z5ycl;
	}


	public BigDecimal getN5sbcp() {
		return this.n5sbcp;
	}

	public void setN5sbcp(BigDecimal n5sbcp) {
		this.n5sbcp = n5sbcp;
	}


	public BigDecimal getN5sccp() {
		return this.n5sccp;
	}

	public void setN5sccp(BigDecimal n5sccp) {
		this.n5sccp = n5sccp;
	}


	public BigDecimal getN5sqt() {
		return this.n5sqt;
	}

	public void setN5sqt(BigDecimal n5sqt) {
		this.n5sqt = n5sqt;
	}


	public BigDecimal getN5sycl() {
		return this.n5sycl;
	}

	public void setN5sycl(BigDecimal n5sycl) {
		this.n5sycl = n5sycl;
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

}