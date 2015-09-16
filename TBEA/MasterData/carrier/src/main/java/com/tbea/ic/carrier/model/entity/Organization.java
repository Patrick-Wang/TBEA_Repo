package com.tbea.ic.carrier.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization {
	
	//办证机构名称
	@Column(name = "bzjgmcs")
	String bzjgmcs;//	:	海南省澄迈县质量技术监督局
	
	//办证日期
	@Column(name = "bzrq")
	String bzrq;//	:	2012-07-06
			
	//录入机构代码
	@Column(name = "entryJgdm")
	String entryJgdm;//	:	
			
	//机构代码
	@Id
	@Column(name = "jgdm")
	String jgdm;//	:	557373525
			
	//机构地址
	@Column(name = "jgdz")
	String jgdz;//	:	海南省澄迈县老城高新技术示范区(海南生态软件园)
		
	//机构类型
	@Column(name = "jglx")
	String jglx;//	:	企业法人                                    
			
	//机构名称
	@Column(name = "jgmc")
	String jgmc;//	:	东软集团(海南)有限公司

	@Column(name = "ly")
	String ly;//	:	ZK_Y
			
	@Column(name = "reservea")
	String reservea;//	:	

	@Column(name = "rowNum")
	String rowNum;//	:	
			
	//注册号
	@Column(name = "zch")
	String zch;//	:	469027000015743
			
	//注册日期
	@Column(name = "zcrq")
	String zcrq;//	:	2010-07-26
			
	//作废日期
	@Column(name = "zfrq")
	String zfrq;//	:	2015-07-26

	@Column(name = "lb")
	String lb;//  	:	

	/**
	 * @return the bzjgmcs
	 */
	public String getBzjgmcs() {
		return bzjgmcs;
	}

	/**
	 * @return the bzrq
	 */
	public String getBzrq() {
		return bzrq;
	}

	/**
	 * @return the entryJgdm
	 */
	public String getEntryJgdm() {
		return entryJgdm;
	}

	/**
	 * @return the jgdm
	 */
	public String getJgdm() {
		return jgdm;
	}

	/**
	 * @return the jgdz
	 */
	public String getJgdz() {
		return jgdz;
	}

	/**
	 * @return the jglx
	 */
	public String getJglx() {
		return jglx;
	}

	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}

	/**
	 * @return the ly
	 */
	public String getLy() {
		return ly;
	}

	/**
	 * @return the reservea
	 */
	public String getReservea() {
		return reservea;
	}

	/**
	 * @return the rowNum
	 */
	public String getRowNum() {
		return rowNum;
	}

	/**
	 * @return the zch
	 */
	public String getZch() {
		return zch;
	}

	/**
	 * @return the zcrq
	 */
	public String getZcrq() {
		return zcrq;
	}

	/**
	 * @return the zfrq
	 */
	public String getZfrq() {
		return zfrq;
	}

	/**
	 * @return the lb
	 */
	public String getLb() {
		return lb;
	}

	/**
	 * @param bzjgmcs the bzjgmcs to set
	 */
	public void setBzjgmcs(String bzjgmcs) {
		this.bzjgmcs = bzjgmcs;
	}

	/**
	 * @param bzrq the bzrq to set
	 */
	public void setBzrq(String bzrq) {
		this.bzrq = bzrq;
	}

	/**
	 * @param entryJgdm the entryJgdm to set
	 */
	public void setEntryJgdm(String entryJgdm) {
		this.entryJgdm = entryJgdm;
	}

	/**
	 * @param jgdm the jgdm to set
	 */
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	/**
	 * @param jgdz the jgdz to set
	 */
	public void setJgdz(String jgdz) {
		this.jgdz = jgdz;
	}

	/**
	 * @param jglx the jglx to set
	 */
	public void setJglx(String jglx) {
		this.jglx = jglx;
	}

	/**
	 * @param jgmc the jgmc to set
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	/**
	 * @param ly the ly to set
	 */
	public void setLy(String ly) {
		this.ly = ly;
	}

	/**
	 * @param reservea the reservea to set
	 */
	public void setReservea(String reservea) {
		this.reservea = reservea;
	}

	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @param zch the zch to set
	 */
	public void setZch(String zch) {
		this.zch = zch;
	}

	/**
	 * @param zcrq the zcrq to set
	 */
	public void setZcrq(String zcrq) {
		this.zcrq = zcrq;
	}

	/**
	 * @param zfrq the zfrq to set
	 */
	public void setZfrq(String zfrq) {
		this.zfrq = zfrq;
	}

	/**
	 * @param lb the lb to set
	 */
	public void setLb(String lb) {
		this.lb = lb;
	}
}
