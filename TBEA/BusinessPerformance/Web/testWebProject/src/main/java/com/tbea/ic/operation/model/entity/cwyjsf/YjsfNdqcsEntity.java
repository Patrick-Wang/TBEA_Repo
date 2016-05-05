package com.tbea.ic.operation.model.entity.cwyjsf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "cwgb_ndqcs")
public class YjsfNdqcsEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer dwid;
	Integer sz;
	Double qcs;
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
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
	public Double getQcs() {
		return qcs;
	}
	public void setQcs(Double qcs) {
		this.qcs = qcs;
	}

}
