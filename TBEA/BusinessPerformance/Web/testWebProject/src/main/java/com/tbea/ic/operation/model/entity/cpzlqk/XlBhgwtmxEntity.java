package com.tbea.ic.operation.model.entity.cpzlqk;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlBhglbEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlZrlbEntity;


@Entity
@Table(name = "cpzlqk_bhgwtmx_xl")
public class XlBhgwtmxEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer nf;
	Integer yf;
	Integer dwid;
	String cplx;
	String sch;
	String cpxh;
	Double bhgsl;
	String sybhgxx;
	XlBhglbEntity bhglx;//bhglxid;
	String yyfx;
	String clcs;
	String cljg;
	XlZrlbEntity zrlb;//zrlbid;
	Timestamp xgsj;
	Timestamp shsj;
	Integer zt;
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	public Integer getYf() {
		return yf;
	}
	public void setYf(Integer yf) {
		this.yf = yf;
	}
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	public String getCplx() {
		return cplx;
	}
	public void setCplx(String cplx) {
		this.cplx = cplx;
	}
	public String getSch() {
		return sch;
	}
	public void setSch(String sch) {
		this.sch = sch;
	}
	public String getCpxh() {
		return cpxh;
	}
	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}
	public Double getBhgsl() {
		return bhgsl;
	}
	public void setBhgsl(Double bhgsl) {
		this.bhgsl = bhgsl;
	}
	public String getSybhgxx() {
		return sybhgxx;
	}
	public void setSybhgxx(String sybhgxx) {
		this.sybhgxx = sybhgxx;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bhglxid")
	public XlBhglbEntity getBhglx() {
		return bhglx;
	}
	public void setBhglx(XlBhglbEntity bhglx) {
		this.bhglx = bhglx;
	}
	public String getYyfx() {
		return yyfx;
	}
	public void setYyfx(String yyfx) {
		this.yyfx = yyfx;
	}
	public String getClcs() {
		return clcs;
	}
	public void setClcs(String clcs) {
		this.clcs = clcs;
	}
	public String getCljg() {
		return cljg;
	}
	public void setCljg(String cljg) {
		this.cljg = cljg;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zrlbid")
	public XlZrlbEntity getZrlb() {
		return zrlb;
	}
	public void setZrlb(XlZrlbEntity zrlb) {
		this.zrlb = zrlb;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	public Timestamp getShsj() {
		return shsj;
	}
	public void setShsj(Timestamp shsj) {
		this.shsj = shsj;
	}

}
