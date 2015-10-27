package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jygk_zzy_bglx database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_bglx")
@NamedQuery(name="JygkZzyBglx.findAll", query="SELECT j FROM JygkZzyBglx j")
public class JygkZzyBglx extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	public JygkZzyBglx() {
	}


	@Id
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}