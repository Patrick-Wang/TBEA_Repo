package com.tbea.ic.operation.model.entity.nyzbscqk;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "identifier_nyzbsckqmatch")
public class NyCompMiningAreaMatchEntity extends AbstractReadWriteEntity implements Serializable {
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

	Integer dwid;
	NyMiningAreaEntity kq;
	NyMzEntity mz;
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kqid")
	public NyMiningAreaEntity getKq() {
		return kq;
	}
	public void setKq(NyMiningAreaEntity kq) {
		this.kq = kq;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mzid")
	public NyMzEntity getMz() {
		return mz;
	}
	public void setMz(NyMzEntity mz) {
		this.mz = mz;
	}
	

	
}
