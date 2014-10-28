package com.tbea.test.testWebProject.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "QYZJK")
public class QYZJK extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String QYBH;

	private String QYMC;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name = "QYBH")
	public String getQYBH() {
		return QYBH;
	}

	public void setQYBH(String qYBH) {
		QYBH = qYBH;
	}

	@Column(name = "QYMC")
	public String getQYMC() {
		return QYMC;
	}

	public void setQYMC(String qYMC) {
		QYMC = qYMC;
	}

}
