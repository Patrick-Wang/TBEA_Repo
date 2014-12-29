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
@Table(name = "yszk_zj_htfkfstj_xl_nwfk")
public class XLNWFKFS extends AbstractReadWriteEntity {
	Date gxrq;
	String ny;
	String gsbm;
	String	sfjzzb	;
	Integer	nwhtddzlbs	;
	Double	nwhtddzlje	;
	Integer	n_1_6_2_1bs	;
	Double	n_1_6_2_1je	;
	Integer	n_1_2_6_1bs	;
	Double	n_1_2_6_1je	;
	Integer	n_0_09_01bs	;
	Double	n_0_09_01je	;
	Integer	qtbs	;
	Double	qtje	;
	Integer	zbqcgynhtbs	;
	Double	zbqcgynhtje	;

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

	@Column(name="[sfjzzb]")
	public String getSfjzzb() {
		return sfjzzb;
	}

	@Column(name="[nwhtddzlbs]")
	public Integer getNwhtddzlbs() {
		return nwhtddzlbs;
	}

	@Column(name="[nwhtddzlje]")
	public Double getNwhtddzlje() {
		return nwhtddzlje;
	}

	@Column(name="[1_6_2_1bs]")
	public Integer getN_1_6_2_1bs() {
		return n_1_6_2_1bs;
	}

	@Column(name="[1_6_2_1je]")
	public Double getN_1_6_2_1je() {
		return n_1_6_2_1je;
	}

	@Column(name="[1_2_6_1bs]")
	public Integer getN_1_2_6_1bs() {
		return n_1_2_6_1bs;
	}

	@Column(name="[1_2_6_1je]")
	public Double getN_1_2_6_1je() {
		return n_1_2_6_1je;
	}

	@Column(name="[0_09_01bs]")
	public Integer getN_0_09_01bs() {
		return n_0_09_01bs;
	}

	@Column(name="[0_09_01je]")
	public Double getN_0_09_01je() {
		return n_0_09_01je;
	}

	@Column(name="[qtbs]")
	public Integer getQtbs() {
		return qtbs;
	}

	@Column(name="[qtje]")
	public Double getQtje() {
		return qtje;
	}

	@Column(name="[zbqcgynhtbs]")
	public Integer getZbqcgynhtbs() {
		return zbqcgynhtbs;
	}

	@Column(name="[zbqcgynhtje]")
	public Double getZbqcgynhtje() {
		return zbqcgynhtje;
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
	 * @param sfjzzb the sfjzzb to set
	 */
	public void setSfjzzb(String sfjzzb) {
		this.sfjzzb = sfjzzb;
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
	 * @param n_1_6_2_1bs the n_1_6_2_1bs to set
	 */
	public void setN_1_6_2_1bs(Integer n_1_6_2_1bs) {
		this.n_1_6_2_1bs = n_1_6_2_1bs;
	}

	/**
	 * @param n_1_6_2_1je the n_1_6_2_1je to set
	 */
	public void setN_1_6_2_1je(Double n_1_6_2_1je) {
		this.n_1_6_2_1je = n_1_6_2_1je;
	}

	/**
	 * @param n_1_2_6_1bs the n_1_2_6_1bs to set
	 */
	public void setN_1_2_6_1bs(Integer n_1_2_6_1bs) {
		this.n_1_2_6_1bs = n_1_2_6_1bs;
	}

	/**
	 * @param n_1_2_6_1je the n_1_2_6_1je to set
	 */
	public void setN_1_2_6_1je(Double n_1_2_6_1je) {
		this.n_1_2_6_1je = n_1_2_6_1je;
	}

	/**
	 * @param n_0_09_01bs the n_0_09_01bs to set
	 */
	public void setN_0_09_01bs(Integer n_0_09_01bs) {
		this.n_0_09_01bs = n_0_09_01bs;
	}

	/**
	 * @param n_0_09_01je the n_0_09_01je to set
	 */
	public void setN_0_09_01je(Double n_0_09_01je) {
		this.n_0_09_01je = n_0_09_01je;
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
	 * @param zbqcgynhtbs the zbqcgynhtbs to set
	 */
	public void setZbqcgynhtbs(Integer zbqcgynhtbs) {
		this.zbqcgynhtbs = zbqcgynhtbs;
	}

	/**
	 * @param zbqcgynhtje the zbqcgynhtje to set
	 */
	public void setZbqcgynhtje(Double zbqcgynhtje) {
		this.zbqcgynhtje = zbqcgynhtje;
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
