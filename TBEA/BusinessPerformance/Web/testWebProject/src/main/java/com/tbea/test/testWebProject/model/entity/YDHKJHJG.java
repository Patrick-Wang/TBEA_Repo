package com.tbea.test.testWebProject.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yszk_zj_ydhkjhjgb")
public class YDHKJHJG {
	Integer ID;
	Date gxrq;
	Double gsbm;
	Double qbkhyqyszk;
	Double qbkhyqk;
	Double qbkhwdqyszk;
	Double qbkhwdqk;
	Double zqkhyqyszk;
	Double zqkhyqk;
	Double zqkhwdqyszk;
	Double zqkhwdqk;
	Double xyqsk;
	Double gyqsk;
	Double jtxdydzjhlzb;
	String sfdrwc;
	Integer qybh;
	/**
	 * @return the iD
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getID() {
		return ID;
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
	public Double getGsbm() {
		return gsbm;
	}
	/**
	 * @return the qbkhyqyszk
	 */
	public Double getQbkhyqyszk() {
		return qbkhyqyszk;
	}
	/**
	 * @return the qbkhyqk
	 */
	public Double getQbkhyqk() {
		return qbkhyqk;
	}
	/**
	 * @return the qbkhwdqyszk
	 */
	public Double getQbkhwdqyszk() {
		return qbkhwdqyszk;
	}
	/**
	 * @return the qbkhwdqk
	 */
	public Double getQbkhwdqk() {
		return qbkhwdqk;
	}
	/**
	 * @return the zqkhyqyszk
	 */
	public Double getZqkhyqyszk() {
		return zqkhyqyszk;
	}
	/**
	 * @return the zqkhyqk
	 */
	public Double getZqkhyqk() {
		return zqkhyqk;
	}
	/**
	 * @return the zqkhwdqyszk
	 */
	public Double getZqkhwdqyszk() {
		return zqkhwdqyszk;
	}
	/**
	 * @return the zqkhwdqk
	 */
	public Double getZqkhwdqk() {
		return zqkhwdqk;
	}
	/**
	 * @return the xyqsk
	 */
	public Double getXyqsk() {
		return xyqsk;
	}
	/**
	 * @return the gyqsk
	 */
	public Double getGyqsk() {
		return gyqsk;
	}
	/**
	 * @return the jtxdydzjhlzb
	 */
	public Double getJtxdydzjhlzb() {
		return jtxdydzjhlzb;
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
	 * @param iD the iD to set
	 */
	public void setID(Integer iD) {
		ID = iD;
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
	public void setGsbm(Double gsbm) {
		this.gsbm = gsbm;
	}
	/**
	 * @param qbkhyqyszk the qbkhyqyszk to set
	 */
	public void setQbkhyqyszk(Double qbkhyqyszk) {
		this.qbkhyqyszk = qbkhyqyszk;
	}
	/**
	 * @param qbkhyqk the qbkhyqk to set
	 */
	public void setQbkhyqk(Double qbkhyqk) {
		this.qbkhyqk = qbkhyqk;
	}
	/**
	 * @param qbkhwdqyszk the qbkhwdqyszk to set
	 */
	public void setQbkhwdqyszk(Double qbkhwdqyszk) {
		this.qbkhwdqyszk = qbkhwdqyszk;
	}
	/**
	 * @param qbkhwdqk the qbkhwdqk to set
	 */
	public void setQbkhwdqk(Double qbkhwdqk) {
		this.qbkhwdqk = qbkhwdqk;
	}
	/**
	 * @param zqkhyqyszk the zqkhyqyszk to set
	 */
	public void setZqkhyqyszk(Double zqkhyqyszk) {
		this.zqkhyqyszk = zqkhyqyszk;
	}
	/**
	 * @param zqkhyqk the zqkhyqk to set
	 */
	public void setZqkhyqk(Double zqkhyqk) {
		this.zqkhyqk = zqkhyqk;
	}
	/**
	 * @param zqkhwdqyszk the zqkhwdqyszk to set
	 */
	public void setZqkhwdqyszk(Double zqkhwdqyszk) {
		this.zqkhwdqyszk = zqkhwdqyszk;
	}
	/**
	 * @param zqkhwdqk the zqkhwdqk to set
	 */
	public void setZqkhwdqk(Double zqkhwdqk) {
		this.zqkhwdqk = zqkhwdqk;
	}
	/**
	 * @param xyqsk the xyqsk to set
	 */
	public void setXyqsk(Double xyqsk) {
		this.xyqsk = xyqsk;
	}
	/**
	 * @param gyqsk the gyqsk to set
	 */
	public void setGyqsk(Double gyqsk) {
		this.gyqsk = gyqsk;
	}
	/**
	 * @param jtxdydzjhlzb the jtxdydzjhlzb to set
	 */
	public void setJtxdydzjhlzb(Double jtxdydzjhlzb) {
		this.jtxdydzjhlzb = jtxdydzjhlzb;
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
