package com.tbea.ic.operation.model.entity.chgb;

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

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;

@Entity
@Table(name = "chgb_jykc")
public class ChJykcEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	Integer nf;
	Integer yf;
	DWXX dwxx;
	JykcxmEntity jykcxmEntity;
	Double syye;
	Double byxz;
	Double bycz;
	Double qmye;
	Integer zt;
	
	public Integer getNf() {
		return nf;
	}


	public void setNf(Integer nf) {
		this.nf = nf;
	}


	public Integer getZt() {
		return zt;
	}


	public void setZt(Integer zt) {
		this.zt = zt;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jykcxmid")
	public JykcxmEntity getJykcxmEntity() {
		return jykcxmEntity;
	}


	public void setJykcxmEntity(JykcxmEntity jykcxmEntity) {
		this.jykcxmEntity = jykcxmEntity;
	}


	public Double getSyye() {
		return syye;
	}


	public void setSyye(Double syye) {
		this.syye = syye;
	}


	public Double getByxz() {
		return byxz;
	}


	public void setByxz(Double byxz) {
		this.byxz = byxz;
	}


	public Double getBycz() {
		return bycz;
	}


	public void setBycz(Double bycz) {
		this.bycz = bycz;
	}


	public Double getQmye() {
		return qmye;
	}


	public void setQmye(Double qmye) {
		this.qmye = qmye;
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
