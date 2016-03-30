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
@Table(name = "pricelib_jcycljg_lwg")
public class LwgEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double sh1214mm;
	Double hz1214mm;
	Double nj1214mm;
	Double tj1214mm;
	Double sh1625mm;
	Double hz1625mm;
	Double nj1625mm;
	Double tj1625mm;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getSh1214mm() {
		return sh1214mm;
	}

	public void setSh1214mm(Double sh1214mm) {
		this.sh1214mm = sh1214mm;
	}

	public Double getHz1214mm() {
		return hz1214mm;
	}

	public void setHz1214mm(Double hz1214mm) {
		this.hz1214mm = hz1214mm;
	}

	public Double getNj1214mm() {
		return nj1214mm;
	}

	public void setNj1214mm(Double nj1214mm) {
		this.nj1214mm = nj1214mm;
	}

	public Double getTj1214mm() {
		return tj1214mm;
	}

	public void setTj1214mm(Double tj1214mm) {
		this.tj1214mm = tj1214mm;
	}

	public Double getSh1625mm() {
		return sh1625mm;
	}

	public void setSh1625mm(Double sh1625mm) {
		this.sh1625mm = sh1625mm;
	}

	public Double getHz1625mm() {
		return hz1625mm;
	}

	public void setHz1625mm(Double hz1625mm) {
		this.hz1625mm = hz1625mm;
	}

	public Double getNj1625mm() {
		return nj1625mm;
	}

	public void setNj1625mm(Double nj1625mm) {
		this.nj1625mm = nj1625mm;
	}

	public Double getTj1625mm() {
		return tj1625mm;
	}

	public void setTj1625mm(Double tj1625mm) {
		this.tj1625mm = tj1625mm;
	}

}
