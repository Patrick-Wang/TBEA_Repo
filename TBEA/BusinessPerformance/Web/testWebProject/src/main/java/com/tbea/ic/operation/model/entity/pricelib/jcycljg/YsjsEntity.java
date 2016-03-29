package com.tbea.ic.operation.model.entity.pricelib.jcycljg;
 
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


@Entity
@Table(name = "priceLib_jcycljg_ysjs")
public class YsjsEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double cjxhCu;
	

	Double cjxhAl;
	Double cjxhZn;
	Double LEMCu;
	Double LEMAl;
	Double LEMZn;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getCjxhCu() {
		return cjxhCu;
	}
	public void setCjxhCu(Double cjxhCu) {
		this.cjxhCu = cjxhCu;
	}
	public Double getCjxhAl() {
		return cjxhAl;
	}
	public void setCjxhAl(Double cjxhAl) {
		this.cjxhAl = cjxhAl;
	}
	public Double getCjxhZn() {
		return cjxhZn;
	}
	public void setCjxhZn(Double cjxhZn) {
		this.cjxhZn = cjxhZn;
	}
	public Double getLEMCu() {
		return LEMCu;
	}
	public void setLEMCu(Double lEMCu) {
		LEMCu = lEMCu;
	}
	public Double getLEMAl() {
		return LEMAl;
	}
	public void setLEMAl(Double lEMAl) {
		LEMAl = lEMAl;
	}
	public Double getLEMZn() {
		return LEMZn;
	}
	public void setLEMZn(Double lEMZn) {
		LEMZn = lEMZn;
	}


}
