package com.tbea.ic.operation.model.entity.cpzlqkyd;

import java.io.Serializable;

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

import com.tbea.ic.operation.model.entity.identifier.sbdcpzlqk.BhglbByqEntity;
import com.tbea.ic.operation.model.entity.identifier.sbdcpzlqk.ZrlbByqEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "sbdcpzlqk_bhgwtmx_xl")
public class BhgmxXlEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	
	Integer nf;
	Integer yf;
	DWXX dwxx;
	String cplx;
	String sch;
	String cpxh;
	Integer bhgsl;
	String sybhgxx;
	BhglbByqEntity bhglb;
	String yyfx;
	String clcs;
	String cljg;
	ZrlbByqEntity zrlb;
	Integer zt;

	
	public Integer getBhgsl() {
		return bhgsl;
	}

	public void setBhgsl(Integer bhgsl) {
		this.bhgsl = bhgsl;
	}

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
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

	public String getSybhgxx() {
		return sybhgxx;
	}

	public void setSybhgxx(String sybhgxx) {
		this.sybhgxx = sybhgxx;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bhglxid")
	public BhglbByqEntity getBhglb() {
		return bhglb;
	}

	public void setBhglb(BhglbByqEntity bhglb) {
		this.bhglb = bhglb;
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
	public ZrlbByqEntity getZrlb() {
		return zrlb;
	}

	public void setZrlb(ZrlbByqEntity zrlb) {
		this.zrlb = zrlb;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
