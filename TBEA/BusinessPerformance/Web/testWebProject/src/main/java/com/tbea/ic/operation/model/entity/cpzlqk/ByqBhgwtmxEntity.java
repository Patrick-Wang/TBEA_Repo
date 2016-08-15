package com.tbea.ic.operation.model.entity.cpzlqk;

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

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqBhglbEntity;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqZrlbEntity;


@Entity
@Table(name = "cpzlqk_bhgwtmx_byq")
public class ByqBhgwtmxEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	private static final long serialVersionUID = 1L;
	
	Integer nf;
	Integer yf;
	Integer dwid;
	//不需要统计方式
	//Integer tjfs;//  --0  --> 110kv 以上   1 --> 配变产品
	String cplx;
	String sch;
	String cpxh;
	String sybhgxx;
	ByqBhglbEntity bhglb;// bhglxid;
	String yyfx;
	String clcs;
	String cljg;
	ByqZrlbEntity zrlb;// zrlbid;
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
//	public Integer getTjfs() {
//		return tjfs;
//	}
//	public void setTjfs(Integer tjfs) {
//		this.tjfs = tjfs;
//	}
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
	public ByqBhglbEntity getBhglb() {
		return bhglb;
	}
	public void setBhglb(ByqBhglbEntity bhglb) {
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
	public ByqZrlbEntity getZrlb() {
		return zrlb;
	}
	public void setZrlb(ByqZrlbEntity zrlb) {
		this.zrlb = zrlb;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}


}
