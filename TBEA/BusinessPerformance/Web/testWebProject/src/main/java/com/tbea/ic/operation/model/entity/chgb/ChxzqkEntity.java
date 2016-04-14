package com.tbea.ic.operation.model.entity.chgb;

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


@Entity
@Table(name = "chgb_chxzqk")
public class ChxzqkEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	Integer nf;
	Integer yf;
	DWXX dwxx;
	Double ycl;
	Double bcp;
	Double sjkcsp;
	Double yfhwkp;
	Double qhfdyk;
	Double qhpcyk;
	Double wfhykp;
	Double qt;
	
	
	
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



	public Double getYcl() {
		return ycl;
	}



	public void setYcl(Double ycl) {
		this.ycl = ycl;
	}



	public Double getBcp() {
		return bcp;
	}



	public void setBcp(Double bcp) {
		this.bcp = bcp;
	}



	public Double getSjkcsp() {
		return sjkcsp;
	}



	public void setSjkcsp(Double sjkcsp) {
		this.sjkcsp = sjkcsp;
	}



	public Double getYfhwkp() {
		return yfhwkp;
	}



	public void setYfhwkp(Double yfhwkp) {
		this.yfhwkp = yfhwkp;
	}



	public Double getQhfdyk() {
		return qhfdyk;
	}



	public void setQhfdyk(Double qhfdyk) {
		this.qhfdyk = qhfdyk;
	}



	public Double getQhpcyk() {
		return qhpcyk;
	}



	public void setQhpcyk(Double qhpcyk) {
		this.qhpcyk = qhpcyk;
	}



	public Double getWfhykp() {
		return wfhykp;
	}



	public void setWfhykp(Double wfhykp) {
		this.wfhykp = wfhykp;
	}



	public Double getQt() {
		return qt;
	}



	public void setQt(Double qt) {
		this.qt = qt;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
