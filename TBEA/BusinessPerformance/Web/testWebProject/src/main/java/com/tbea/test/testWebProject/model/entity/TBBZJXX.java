package com.tbea.test.testWebProject.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszk_zj_tbbzjxx")
public class TBBZJXX  extends AbstractReadWriteEntity{
	
	Date gxrq;
	String gsbm;
	Integer nf;
	Integer yf;
	Double je;
	String sfdrwc;
	Integer qybh;
	/**
	 * @return the iD
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	/**
	 * @return the gxrq
	 */
	public Date getGxrq() {
		return gxrq;
	}
	/**
	 * @return the gsbm
	 */
	public String getGsbm() {
		return gsbm;
	}
	/**
	 * @return the nf
	 */
	public Integer getNf() {
		return nf;
	}
	/**
	 * @return the yf
	 */
	public Integer getYf() {
		return yf;
	}
	/**
	 * @return the je
	 */
	public Double getJe() {
		return je;
	}
	/**
	 * @return the sfdrwc
	 */
	public String getSfdrwc() {
		return sfdrwc;
	}
	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}
	/**
	 * @param gsbm the gsbm to set
	 */
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}
	/**
	 * @param nf the nf to set
	 */
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	/**
	 * @param yf the yf to set
	 */
	public void setYf(Integer yf) {
		this.yf = yf;
	}
	/**
	 * @param je the je to set
	 */
	public void setJe(Double je) {
		this.je = je;
	}
	/**
	 * @param sfdrwc the sfdrwc to set
	 */
	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}
	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

}
