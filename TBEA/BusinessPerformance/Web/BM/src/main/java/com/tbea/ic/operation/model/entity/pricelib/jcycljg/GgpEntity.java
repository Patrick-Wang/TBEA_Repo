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
@Table(name = "pricelib_jcycljg_ggp")
public class GgpEntity extends AbstractReadWriteEntity implements Serializable {
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
	Double wg30q120;
	Double wg30pk100;
	Double wg27pk095;
	Double wg23pk085;
	Double bgb30p120;
	Double bgb30p110;
	Double bgb27r095;
	Double bgb27r085;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getWg30q120() {
		return wg30q120;
	}
	public void setWg30q120(Double wg30q120) {
		this.wg30q120 = wg30q120;
	}
	public Double getWg30pk100() {
		return wg30pk100;
	}
	public void setWg30pk100(Double wg30pk100) {
		this.wg30pk100 = wg30pk100;
	}
	public Double getWg27pk095() {
		return wg27pk095;
	}
	public void setWg27pk095(Double wg27pk095) {
		this.wg27pk095 = wg27pk095;
	}
	public Double getWg23pk085() {
		return wg23pk085;
	}
	public void setWg23pk085(Double wg23pk085) {
		this.wg23pk085 = wg23pk085;
	}
	public Double getBgb30p120() {
		return bgb30p120;
	}
	public void setBgb30p120(Double bgb30p120) {
		this.bgb30p120 = bgb30p120;
	}
	public Double getBgb30p110() {
		return bgb30p110;
	}
	public void setBgb30p110(Double bgb30p110) {
		this.bgb30p110 = bgb30p110;
	}
	public Double getBgb27r095() {
		return bgb27r095;
	}
	public void setBgb27r095(Double bgb27r095) {
		this.bgb27r095 = bgb27r095;
	}
	public Double getBgb27r085() {
		return bgb27r085;
	}
	public void setBgb27r085(Double bgb27r085) {
		this.bgb27r085 = bgb27r085;
	}
	
	
}
