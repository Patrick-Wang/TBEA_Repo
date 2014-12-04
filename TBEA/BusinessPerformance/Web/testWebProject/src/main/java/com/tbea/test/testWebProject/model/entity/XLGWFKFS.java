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
@Table(name = "yszk_zj_htfkfstj_xl_gwfk")
public class XLGWFKFS extends AbstractReadWriteEntity {
	Date gxrq;
	String gsbm;
	String	sfjzzb	;
	Integer	gwhtddzlbs	;
	Double	gwhtddzlje	;
	Integer	n3_06_0_01bs	;
	Double	n3_06_0_01je	;
	Integer	n0_09_0_01bs	;
	Double	n0_09_0_01je	;
	Integer	n3_4_2_1bs	;
	Double	n3_4_2_1je	;
	Integer	n2_5_2_1bs	;
	Double	n2_5_2_1je	;
	Integer	n2_5_2d5_0d5bs	;
	Double	n2_5_2d5_0d5je	;
	Integer	n0_10_0_0bs	;
	Double	n0_10_0_0je	;
	Integer	n0_9d5_0d5bs	;
	Double	n0_9d5_0d5je	;
	Integer	qtbs	;
	Double	qtje	;
	Integer	wzbjhtbs	;
	Double	wzbjhtje	;
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

	@Column(name="[gwhtddzlbs]")
	public Integer getGwhtddzlbs() {
		return gwhtddzlbs;
	}

	@Column(name="[gwhtddzlje]")
	public Double getGwhtddzlje() {
		return gwhtddzlje;
	}

	@Column(name="[3_06_0_01bs]")
	public Integer getN3_06_0_01bs() {
		return n3_06_0_01bs;
	}

	@Column(name="[3_06_0_01je]")
	public Double getN3_06_0_01je() {
		return n3_06_0_01je;
	}

	@Column(name="[0_09_0_01bs]")
	public Integer getN0_09_0_01bs() {
		return n0_09_0_01bs;
	}

	@Column(name="[0_09_0_01je]")
	public Double getN0_09_0_01je() {
		return n0_09_0_01je;
	}

	@Column(name="[3_4_2_1bs]")
	public Integer getN3_4_2_1bs() {
		return n3_4_2_1bs;
	}

	@Column(name="[3_4_2_1je]")
	public Double getN3_4_2_1je() {
		return n3_4_2_1je;
	}

	@Column(name="[2_5_2_1bs]")
	public Integer getN2_5_2_1bs() {
		return n2_5_2_1bs;
	}

	@Column(name="[2_5_2_1je]")
	public Double getN2_5_2_1je() {
		return n2_5_2_1je;
	}

	@Column(name="[2_5_2d5_0d5bs]")
	public Integer getN2_5_2d5_0d5bs() {
		return n2_5_2d5_0d5bs;
	}

	@Column(name="[2_5_2d5_0d5je]")
	public Double getN2_5_2d5_0d5je() {
		return n2_5_2d5_0d5je;
	}

	@Column(name="[0_10_0_0bs]")
	public Integer getN0_10_0_0bs() {
		return n0_10_0_0bs;
	}

	@Column(name="[0_10_0_0je]")
	public Double getN0_10_0_0je() {
		return n0_10_0_0je;
	}

	@Column(name="[0_9d5_0d5bs]")
	public Integer getN0_9d5_0d5bs() {
		return n0_9d5_0d5bs;
	}

	@Column(name="[0_9d5_0d5je]")
	public Double getN0_9d5_0d5je() {
		return n0_9d5_0d5je;
	}

	@Column(name="[qtbs]")
	public Integer getQtbs() {
		return qtbs;
	}

	@Column(name="[qtje]")
	public Double getQtje() {
		return qtje;
	}

	@Column(name="[wzbjhtbs]")
	public Integer getWzbjhtbs() {
		return wzbjhtbs;
	}

	@Column(name="[wzbjhtje]")
	public Double getWzbjhtje() {
		return wzbjhtje;
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
	 * @param n3_06_0_01bs the n3_06_0_01bs to set
	 */
	public void setN3_06_0_01bs(Integer n3_06_0_01bs) {
		this.n3_06_0_01bs = n3_06_0_01bs;
	}

	/**
	 * @param n3_06_0_01je the n3_06_0_01je to set
	 */
	public void setN3_06_0_01je(Double n3_06_0_01je) {
		this.n3_06_0_01je = n3_06_0_01je;
	}

	/**
	 * @param n0_09_0_01bs the n0_09_0_01bs to set
	 */
	public void setN0_09_0_01bs(Integer n0_09_0_01bs) {
		this.n0_09_0_01bs = n0_09_0_01bs;
	}

	/**
	 * @param n0_09_0_01je the n0_09_0_01je to set
	 */
	public void setN0_09_0_01je(Double n0_09_0_01je) {
		this.n0_09_0_01je = n0_09_0_01je;
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
	 * @param n2_5_2_1bs the n2_5_2_1bs to set
	 */
	public void setN2_5_2_1bs(Integer n2_5_2_1bs) {
		this.n2_5_2_1bs = n2_5_2_1bs;
	}

	/**
	 * @param n2_5_2_1je the n2_5_2_1je to set
	 */
	public void setN2_5_2_1je(Double n2_5_2_1je) {
		this.n2_5_2_1je = n2_5_2_1je;
	}

	/**
	 * @param n2_5_2d5_0d5bs the n2_5_2d5_0d5bs to set
	 */
	public void setN2_5_2d5_0d5bs(Integer n2_5_2d5_0d5bs) {
		this.n2_5_2d5_0d5bs = n2_5_2d5_0d5bs;
	}

	/**
	 * @param n2_5_2d5_0d5je the n2_5_2d5_0d5je to set
	 */
	public void setN2_5_2d5_0d5je(Double n2_5_2d5_0d5je) {
		this.n2_5_2d5_0d5je = n2_5_2d5_0d5je;
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
	 * @param n0_9d5_0d5bs the n0_9d5_0d5bs to set
	 */
	public void setN0_9d5_0d5bs(Integer n0_9d5_0d5bs) {
		this.n0_9d5_0d5bs = n0_9d5_0d5bs;
	}

	/**
	 * @param n0_9d5_0d5je the n0_9d5_0d5je to set
	 */
	public void setN0_9d5_0d5je(Double n0_9d5_0d5je) {
		this.n0_9d5_0d5je = n0_9d5_0d5je;
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
	 * @param wzbjhtbs the wzbjhtbs to set
	 */
	public void setWzbjhtbs(Integer wzbjhtbs) {
		this.wzbjhtbs = wzbjhtbs;
	}

	/**
	 * @param wzbjhtje the wzbjhtje to set
	 */
	public void setWzbjhtje(Double wzbjhtje) {
		this.wzbjhtje = wzbjhtje;
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

}
