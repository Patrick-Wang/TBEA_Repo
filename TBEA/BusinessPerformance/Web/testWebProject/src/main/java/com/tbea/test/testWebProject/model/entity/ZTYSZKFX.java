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
@Table(name = "yszk_zj_ztyszkfxb")
public class ZTYSZKFX extends AbstractReadWriteEntity{
	Date gxrq;
	String gsbm;
	Double byzmyszkye;
	Double byblkzye;
	Double byyszksjs;
	Double ljsr;
	Double qntqzmyszkye;
	Double qntqblye;
	Double qntqyszksjs;
	Double qntqsr;
	String sfdrwc;
	Integer qybh;
	
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
	 * @return the byzmyszkye
	 */
	public Double getByzmyszkye() {
		return byzmyszkye;
	}

	/**
	 * @return the byblkzye
	 */
	public Double getByblkzye() {
		return byblkzye;
	}

	/**
	 * @return the byyszksjs
	 */
	public Double getByyszksjs() {
		return byyszksjs;
	}

	/**
	 * @return the ljsr
	 */
	public Double getLjsr() {
		return ljsr;
	}

	/**
	 * @return the qntqzmyszkye
	 */
	public Double getQntqzmyszkye() {
		return qntqzmyszkye;
	}

	/**
	 * @return the qntqblye
	 */
	public Double getQntqblye() {
		return qntqblye;
	}

	/**
	 * @return the qntqyszksjs
	 */
	public Double getQntqyszksjs() {
		return qntqyszksjs;
	}

	/**
	 * @return the qntqsr
	 */
	public Double getQntqsr() {
		return qntqsr;
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
	 * @param byzmyszkye the byzmyszkye to set
	 */
	public void setByzmyszkye(Double byzmyszkye) {
		this.byzmyszkye = byzmyszkye;
	}

	/**
	 * @param byblkzye the byblkzye to set
	 */
	public void setByblkzye(Double byblkzye) {
		this.byblkzye = byblkzye;
	}

	/**
	 * @param byyszksjs the byyszksjs to set
	 */
	public void setByyszksjs(Double byyszksjs) {
		this.byyszksjs = byyszksjs;
	}

	/**
	 * @param ljsr the ljsr to set
	 */
	public void setLjsr(Double ljsr) {
		this.ljsr = ljsr;
	}

	/**
	 * @param qntqzmyszkye the qntqzmyszkye to set
	 */
	public void setQntqzmyszkye(Double qntqzmyszkye) {
		this.qntqzmyszkye = qntqzmyszkye;
	}

	/**
	 * @param qntqblye the qntqblye to set
	 */
	public void setQntqblye(Double qntqblye) {
		this.qntqblye = qntqblye;
	}

	/**
	 * @param qntqyszksjs the qntqyszksjs to set
	 */
	public void setQntqyszksjs(Double qntqyszksjs) {
		this.qntqyszksjs = qntqyszksjs;
	}

	/**
	 * @param qntqsr the qntqsr to set
	 */
	public void setQntqsr(Double qntqsr) {
		this.qntqsr = qntqsr;
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
