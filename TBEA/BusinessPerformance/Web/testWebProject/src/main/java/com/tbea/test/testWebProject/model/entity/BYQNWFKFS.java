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
@Table(name = "yszk_zj_htfkfstj_byq_nwfk")
public class BYQNWFKFS   extends AbstractReadWriteEntity{
	
	Date gxrq;
	String gsbm;
	String ny;
	Integer nwhtddzlbs;
	Double nwhtddzlje;
	Integer n_3_3_3_1bs;
	Double n_3_3_3_1je;
	Integer n_1_4_4_0d5_0d5bs;
	Double n_1_4_4_0d5_0d5je;
	Integer n_1_2_6d5_0d5bs;
	Double n_1_2_6d5_0d5je;
	Integer n_1_4_4d5_0d5bs;
	Double n_1_4_4d5_0d5je;
	Integer qtybs;
	Double qtyje;
	Integer qtebs;
	Double qteje;
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

	@Column(name="[gxrq]")
	public Date getGxrq() {
		return gxrq;
	}

	@Column(name="[gsbm]")
	public String getGsbm() {
		return gsbm;
	}

	@Column(name="[nwhtddzlbs]")
	public Integer getNwhtddzlbs() {
		return nwhtddzlbs;
	}

	@Column(name="[nwhtddzlje]")
	public Double getNwhtddzlje() {
		return nwhtddzlje;
	}

	@Column(name="[3_3_3_1bs]")
	public Integer getN_3_3_3_1bs() {
		return n_3_3_3_1bs;
	}

	@Column(name="[3_3_3_1je]")
	public Double getN_3_3_3_1je() {
		return n_3_3_3_1je;
	}

	@Column(name="[1_4_4_0d5_0d5bs]")
	public Integer getN_1_4_4_0d5_0d5bs() {
		return n_1_4_4_0d5_0d5bs;
	}

	@Column(name="[1_4_4_0d5_0d5je]")
	public Double getN_1_4_4_0d5_0d5je() {
		return n_1_4_4_0d5_0d5je;
	}

	@Column(name="[1_2_6d5_0d5bs]")
	public Integer getN_1_2_6d5_0d5bs() {
		return n_1_2_6d5_0d5bs;
	}

	@Column(name="[1_2_6d5_0d5je]")
	public Double getN_1_2_6d5_0d5je() {
		return n_1_2_6d5_0d5je;
	}

	@Column(name="[1_4_4d5_0d5bs]")
	public Integer getN_1_4_4d5_0d5bs() {
		return n_1_4_4d5_0d5bs;
	}

	@Column(name="[1_4_4d5_0d5je]")
	public Double getN_1_4_4d5_0d5je() {
		return n_1_4_4d5_0d5je;
	}

	@Column(name="[qtybs]")
	public Integer getQtybs() {
		return qtybs;
	}

	@Column(name="[qtyje]")
	public Double getQtyje() {
		return qtyje;
	}

	@Column(name="[qtebs]")
	public Integer getQtebs() {
		return qtebs;
	}

	@Column(name="[qteje]")
	public Double getQteje() {
		return qteje;
	}

	@Column(name="[sfdrwc]")
	public String getSfdrwc() {
		return sfdrwc;
	}

	@Column(name="[qybh]")
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
	 * @param nwhtddzlbs the nwhtddzlbs to set
	 */
	public void setNwhtddzlbs(Integer nwhtddzlbs) {
		this.nwhtddzlbs = nwhtddzlbs;
	}

	/**
	 * @param nwhtddzlje the nwhtddzlje to set
	 */
	public void setNwhtddzlje(Double nwhtddzlje) {
		this.nwhtddzlje = nwhtddzlje;
	}

	/**
	 * @param n_3_3_3_1bs the n_3_3_3_1bs to set
	 */
	public void setN_3_3_3_1bs(Integer n_3_3_3_1bs) {
		this.n_3_3_3_1bs = n_3_3_3_1bs;
	}

	/**
	 * @param n_3_3_3_1je the n_3_3_3_1je to set
	 */
	public void setN_3_3_3_1je(Double n_3_3_3_1je) {
		this.n_3_3_3_1je = n_3_3_3_1je;
	}

	/**
	 * @param n_1_4_4_0d5_0d5bs the n_1_4_4_0d5_0d5bs to set
	 */
	public void setN_1_4_4_0d5_0d5bs(Integer n_1_4_4_0d5_0d5bs) {
		this.n_1_4_4_0d5_0d5bs = n_1_4_4_0d5_0d5bs;
	}

	/**
	 * @param n_1_4_4_0d5_0d5je the n_1_4_4_0d5_0d5je to set
	 */
	public void setN_1_4_4_0d5_0d5je(Double n_1_4_4_0d5_0d5je) {
		this.n_1_4_4_0d5_0d5je = n_1_4_4_0d5_0d5je;
	}

	/**
	 * @param n_1_2_6d5_0d5bs the n_1_2_6d5_0d5bs to set
	 */
	public void setN_1_2_6d5_0d5bs(Integer n_1_2_6d5_0d5bs) {
		this.n_1_2_6d5_0d5bs = n_1_2_6d5_0d5bs;
	}

	/**
	 * @param n_1_2_6d5_0d5je the n_1_2_6d5_0d5je to set
	 */
	public void setN_1_2_6d5_0d5je(Double n_1_2_6d5_0d5je) {
		this.n_1_2_6d5_0d5je = n_1_2_6d5_0d5je;
	}

	/**
	 * @param n_1_4_4d5_0d5bs the n_1_4_4d5_0d5bs to set
	 */
	public void setN_1_4_4d5_0d5bs(Integer n_1_4_4d5_0d5bs) {
		this.n_1_4_4d5_0d5bs = n_1_4_4d5_0d5bs;
	}

	/**
	 * @param n_1_4_4d5_0d5je the n_1_4_4d5_0d5je to set
	 */
	public void setN_1_4_4d5_0d5je(Double n_1_4_4d5_0d5je) {
		this.n_1_4_4d5_0d5je = n_1_4_4d5_0d5je;
	}

	/**
	 * @param qtybs the qtybs to set
	 */
	public void setQtybs(Integer qtybs) {
		this.qtybs = qtybs;
	}

	/**
	 * @param qtyje the qtyje to set
	 */
	public void setQtyje(Double qtyje) {
		this.qtyje = qtyje;
	}

	/**
	 * @param qtebs the qtebs to set
	 */
	public void setQtebs(Integer qtebs) {
		this.qtebs = qtebs;
	}

	/**
	 * @param qteje the qteje to set
	 */
	public void setQteje(Double qteje) {
		this.qteje = qteje;
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

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	
}
