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
@Table(name = "yszk_zj_htfkfstj_byq_gwfk")
public class BYQGWFKFS  extends AbstractReadWriteEntity{
	Date gxrq;
	String gsbm;
	String ny;
	Integer gwhtddzlbs;
	Double gwhtddzlje;
	Integer n3_4_2_1bs;
	Double n3_4_2_1je;
	Integer n3_4_2d5_0d5bs;
	Double n3_4_2d5_0d5je;
	Integer n0_9_0_1bs;
	Double n0_9_0_1je;
	Integer n1_4_4_1bs;
	Double n1_4_4_1je;
	Integer n1_4_4d5_0d5bs;
	Double n1_4_4d5_0d5je;
	Integer n0_10_0_0bs;
	Double n0_10_0_0je;
	Integer n9d5_0d5bs;
	Double n9d5_0d5je;
	Integer qtbs;
	Double qtje;
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

	@Column(name="[gwhtddzlbs]")
	public Integer getGwhtddzlbs() {
		return gwhtddzlbs;
	}

	@Column(name="[gwhtddzlje]")
	public Double getGwhtddzlje() {
		return gwhtddzlje;
	}

	@Column(name="[3_4_2_1bs]")
	public Integer getN3_4_2_1bs() {
		return n3_4_2_1bs;
	}

	@Column(name="[3_4_2_1je]")
	public Double getN3_4_2_1je() {
		return n3_4_2_1je;
	}

	@Column(name="[3_4_2d5_0d5bs]")
	public Integer getN3_4_2d5_0d5bs() {
		return n3_4_2d5_0d5bs;
	}

	@Column(name="[3_4_2d5_0d5je]")
	public Double getN3_4_2d5_0d5je() {
		return n3_4_2d5_0d5je;
	}

	@Column(name="[0_9_0_1bs]")
	public Integer getN0_9_0_1bs() {
		return n0_9_0_1bs;
	}

	@Column(name="[0_9_0_1je]")
	public Double getN0_9_0_1je() {
		return n0_9_0_1je;
	}

	@Column(name="[1_4_4_1bs]")
	public Integer getN1_4_4_1bs() {
		return n1_4_4_1bs;
	}

	@Column(name="[1_4_4_1je]")
	public Double getN1_4_4_1je() {
		return n1_4_4_1je;
	}

	@Column(name="[1_4_4d5_0d5bs]")
	public Integer getN1_4_4d5_0d5bs() {
		return n1_4_4d5_0d5bs;
	}

	@Column(name="[1_4_4d5_0d5je]")
	public Double getN1_4_4d5_0d5je() {
		return n1_4_4d5_0d5je;
	}

	@Column(name="[0_10_0_0bs]")
	public Integer getN0_10_0_0bs() {
		return n0_10_0_0bs;
	}

	@Column(name="[0_10_0_0je]")
	public Double getN0_10_0_0je() {
		return n0_10_0_0je;
	}

	@Column(name="[9d5_0d5bs]")
	public Integer getN9d5_0d5bs() {
		return n9d5_0d5bs;
	}

	@Column(name="[9d5_0d5je]")
	public Double getN9d5_0d5je() {
		return n9d5_0d5je;
	}

	@Column(name="[qtbs]")
	public Integer getQtbs() {
		return qtbs;
	}

	@Column(name="[qtje]")
	public Double getQtje() {
		return qtje;
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
	 * @param gwhtddzlbs the gwhtddzlbs to set
	 */
	public void setGwhtddzlbs(Integer gwhtddzlbs) {
		this.gwhtddzlbs = gwhtddzlbs;
	}

	/**
	 * @param gwhtddzlje the gwhtddzlje to set
	 */
	public void setGwhtddzlje(Double gwhtddzlje) {
		this.gwhtddzlje = gwhtddzlje;
	}

	/**
	 * @param n3_4_2_1bs the n3_4_2_1bs to set
	 */
	public void setN3_4_2_1bs(Integer n3_4_2_1bs) {
		this.n3_4_2_1bs = n3_4_2_1bs;
	}

	/**
	 * @param n3_4_2_1je the n3_4_2_1je to set
	 */
	public void setN3_4_2_1je(Double n3_4_2_1je) {
		this.n3_4_2_1je = n3_4_2_1je;
	}

	/**
	 * @param n3_4_2d5_0d5bs the n3_4_2d5_0d5bs to set
	 */
	public void setN3_4_2d5_0d5bs(Integer n3_4_2d5_0d5bs) {
		this.n3_4_2d5_0d5bs = n3_4_2d5_0d5bs;
	}

	/**
	 * @param n3_4_2d5_0d5je the n3_4_2d5_0d5je to set
	 */
	public void setN3_4_2d5_0d5je(Double n3_4_2d5_0d5je) {
		this.n3_4_2d5_0d5je = n3_4_2d5_0d5je;
	}

	/**
	 * @param n0_9_0_1bs the n0_9_0_1bs to set
	 */
	public void setN0_9_0_1bs(Integer n0_9_0_1bs) {
		this.n0_9_0_1bs = n0_9_0_1bs;
	}

	/**
	 * @param n0_9_0_1je the n0_9_0_1je to set
	 */
	public void setN0_9_0_1je(Double n0_9_0_1je) {
		this.n0_9_0_1je = n0_9_0_1je;
	}

	/**
	 * @param n1_4_4_1bs the n1_4_4_1bs to set
	 */
	public void setN1_4_4_1bs(Integer n1_4_4_1bs) {
		this.n1_4_4_1bs = n1_4_4_1bs;
	}

	/**
	 * @param n1_4_4_1je the n1_4_4_1je to set
	 */
	public void setN1_4_4_1je(Double n1_4_4_1je) {
		this.n1_4_4_1je = n1_4_4_1je;
	}

	/**
	 * @param n1_4_4d5_0d5bs the n1_4_4d5_0d5bs to set
	 */
	public void setN1_4_4d5_0d5bs(Integer n1_4_4d5_0d5bs) {
		this.n1_4_4d5_0d5bs = n1_4_4d5_0d5bs;
	}

	/**
	 * @param n1_4_4d5_0d5je the n1_4_4d5_0d5je to set
	 */
	public void setN1_4_4d5_0d5je(Double n1_4_4d5_0d5je) {
		this.n1_4_4d5_0d5je = n1_4_4d5_0d5je;
	}

	/**
	 * @param n0_10_0_0bs the n0_10_0_0bs to set
	 */
	public void setN0_10_0_0bs(Integer n0_10_0_0bs) {
		this.n0_10_0_0bs = n0_10_0_0bs;
	}

	/**
	 * @param n0_10_0_0je the n0_10_0_0je to set
	 */
	public void setN0_10_0_0je(Double n0_10_0_0je) {
		this.n0_10_0_0je = n0_10_0_0je;
	}

	/**
	 * @param n9d5_0d5bs the n9d5_0d5bs to set
	 */
	public void setN9d5_0d5bs(Integer n9d5_0d5bs) {
		this.n9d5_0d5bs = n9d5_0d5bs;
	}

	/**
	 * @param n9d5_0d5je the n9d5_0d5je to set
	 */
	public void setN9d5_0d5je(Double n9d5_0d5je) {
		this.n9d5_0d5je = n9d5_0d5je;
	}

	/**
	 * @param qtbs the qtbs to set
	 */
	public void setQtbs(Integer qtbs) {
		this.qtbs = qtbs;
	}

	/**
	 * @param qtje the qtje to set
	 */
	public void setQtje(Double qtje) {
		this.qtje = qtje;
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
