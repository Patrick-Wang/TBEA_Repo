package com.tbea.ic.operation.model.entity.identifier.cpzlqk;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "identifier_cpzlqk_zrlb_byq")
public class ByqZrlbEntity extends AbstractReadWriteEntity implements Serializable {
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
	String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
