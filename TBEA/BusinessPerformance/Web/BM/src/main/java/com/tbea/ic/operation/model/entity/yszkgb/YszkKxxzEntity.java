package com.tbea.ic.operation.model.entity.yszkgb;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.SHZT;


@Entity
@Table(name = "yszkgb_yszkkxxzqk")
public class YszkKxxzEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer nf;
	Integer yf;
	DWXX dwxx;
	Double yq0z1y;
	Double yq1z3y;
	Double yq3z6y;
	Double yq6z12y;
	Double yq1nys;
	Double wdq;
	Double wdqzbj;
	Integer zt;
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	public Integer getYf() {
		return yf;
	}
	public void setYf(Integer yf) {
		this.yf = yf;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}
	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}
	public Double getYq0z1y() {
		return yq0z1y;
	}
	public void setYq0z1y(Double yq0z1y) {
		this.yq0z1y = yq0z1y;
	}
	public Double getYq1z3y() {
		return yq1z3y;
	}
	public void setYq1z3y(Double yq1z3y) {
		this.yq1z3y = yq1z3y;
	}
	public Double getYq3z6y() {
		return yq3z6y;
	}
	public void setYq3z6y(Double yq3z6y) {
		this.yq3z6y = yq3z6y;
	}
	public Double getYq6z12y() {
		return yq6z12y;
	}
	public void setYq6z12y(Double yq6z12y) {
		this.yq6z12y = yq6z12y;
	}
	public Double getYq1nys() {
		return yq1nys;
	}
	public void setYq1nys(Double yq1nys) {
		this.yq1nys = yq1nys;
	}
	public Double getWdq() {
		return wdq;
	}
	public void setWdq(Double wdq) {
		this.wdq = wdq;
	}
	public Double getWdqzbj() {
		return wdqzbj;
	}
	public void setWdqzbj(Double wdqzbj) {
		this.wdqzbj = wdqzbj;
	}

	public Integer getZt() {
		return zt;
	}
	
	public void setZt(Integer zt) {
		this.zt = zt;
	}

}
