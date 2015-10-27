package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jygk_zzy_fl database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_fl")
@NamedQuery(name="JygkZzyFl.findAll", query="SELECT j FROM JygkZzyFl j")
public class JygkZzyFl extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String viewname;

	public JygkZzyFl() {
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


	public String getViewname() {
		return this.viewname;
	}

	public void setViewname(String viewname) {
		this.viewname = viewname;
	}

}