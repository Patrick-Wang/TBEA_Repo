package com.tbea.ic.operation.model.entity.cpzlqk;

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

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.NameEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "cpzlqk_adwtjjg_byq")
public class ByqAdwtjjgEntity extends AbstractReadWriteEntity implements Serializable {
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

	DWXX dw;
	NameEntity dwmc;
	NameEntity cpdl;
	NameEntity cpxl;
	String formul;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDw() {
		return dw;
	}
	public void setDw(DWXX dw) {
		this.dw = dw;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwmc")
	public NameEntity getDwmc() {
		return dwmc;
	}
	
	
	public void setDwmc(NameEntity dwmc) {
		this.dwmc = dwmc;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpdl")
	public NameEntity getCpdl() {
		return cpdl;
	}
	public void setCpdl(NameEntity cpdl) {
		this.cpdl = cpdl;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpxl")
	public NameEntity getCpxl() {
		return cpxl;
	}
	public void setCpxl(NameEntity cpxl) {
		this.cpxl = cpxl;
	}
	public String getFormul() {
		return formul;
	}
	public void setFormul(String formul) {
		this.formul = formul;
	}
}
