package com.tbea.ic.operation.model.entity.pricelib.jcycljg;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "pricelib_jcycljg_myzs")
public class MyzsEntity extends AbstractReadWriteEntity implements Serializable {
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
	Date date;
	Double myzs;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMyzs() {
		return myzs;
	}

	public void setMyzs(Double myzs) {
		this.myzs = myzs;
	}

}
