package com.tbea.ic.operation.model.entity.cwyjsf;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cwgb_yjsf")
public class YjsfEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer sz;
	Double yjs;
	Double yijs;
	Double wjs;
	Double ljyj;
	Double ljyij;
	Double ljwj;
	Double qms;
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
	public Integer getSz() {
		return sz;
	}
	public void setSz(Integer sz) {
		this.sz = sz;
	}
	public Double getYjs() {
		return yjs;
	}
	public void setYjs(Double yjs) {
		this.yjs = yjs;
	}
	public Double getYijs() {
		return yijs;
	}
	public void setYijs(Double yijs) {
		this.yijs = yijs;
	}
	public Double getWjs() {
		return wjs;
	}
	public void setWjs(Double wjs) {
		this.wjs = wjs;
	}
	public Double getLjyj() {
		return ljyj;
	}
	public void setLjyj(Double ljyj) {
		this.ljyj = ljyj;
	}
	public Double getLjyij() {
		return ljyij;
	}
	public void setLjyij(Double ljyij) {
		this.ljyij = ljyij;
	}
	public Double getLjwj() {
		return ljwj;
	}
	public void setLjwj(Double ljwj) {
		this.ljwj = ljwj;
	}
	public Double getQms() {
		return qms;
	}
	public void setQms(Double qms) {
		this.qms = qms;
	}

}
