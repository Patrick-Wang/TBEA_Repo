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
@Table(name = "pricelib_jcycljg_pmicpippi")
public class PmiCpiPpiEntity extends AbstractReadWriteEntity implements
		Serializable {
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
	Double pmi;
	Double ppi;
	Double cpi;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPmi() {
		return pmi;
	}

	public void setPmi(Double pmi) {
		this.pmi = pmi;
	}

	public Double getPpi() {
		return ppi;
	}

	public void setPpi(Double ppi) {
		this.ppi = ppi;
	}

	public Double getCpi() {
		return cpi;
	}

	public void setCpi(Double cpi) {
		this.cpi = cpi;
	}
}
