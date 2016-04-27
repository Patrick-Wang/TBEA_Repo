package com.tbea.ic.operation.model.entity.identifier.cwgb;

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


@Entity
@Table(name = "identifier_cwgb_cpfl")
public class CpflEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cy")
	CyEntity cy;
	String name;
	
	public CyEntity getCy() {
		return cy;
	}
	public void setCy(CyEntity cy) {
		this.cy = cy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
