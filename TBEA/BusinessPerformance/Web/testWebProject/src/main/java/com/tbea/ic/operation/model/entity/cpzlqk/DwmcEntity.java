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
@Table(name = "cpzlqk_dwmc")
public class DwmcEntity extends AbstractReadWriteEntity implements Serializable {
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDw() {
		return dw;
	}
	public void setDw(DWXX dw) {
		this.dw = dw;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nameid")
	public NameEntity getDwmc() {
		return dwmc;
	}
	public void setDwmc(NameEntity dwmc) {
		this.dwmc = dwmc;
	}
}
