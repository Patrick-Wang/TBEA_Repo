package com.tbea.test.testWebProject.model.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yszk_zj_ydsjhkqk")
public class YDSJHKQK {
	Integer	ID	;
	Date	gxrq	;
	String	gsbm	;
	Double	yqyszksjhk	;
	Double	yqksjhk	;
	Double	wdqyszksjhk	;
	Double	wdqksjhk	;
	Double	qbkhhk	;
	Double	zqkhhk	;
	Double	xkxhhk	;
	Double	jhwhk	;
	String	sfdrwc	;
	Integer	qybh	;
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
	public String getGsbm() {
		return gsbm;
	}
	/**
	 * @return the yqyszksjhk
	 */
	public Double getYqyszksjhk() {
		return yqyszksjhk;
	}
	/**
	 * @return the yqksjhk
	 */
	public Double getYqksjhk() {
		return yqksjhk;
	}
	/**
	 * @return the wdqyszksjhk
	 */
	public Double getWdqyszksjhk() {
		return wdqyszksjhk;
	}
	/**
	 * @return the wdqksjhk
	 */
	public Double getWdqksjhk() {
		return wdqksjhk;
	}
	/**
	 * @return the qbkhhk
	 */
	public Double getQbkhhk() {
		return qbkhhk;
	}
	/**
	 * @return the zqkhhk
	 */
	public Double getZqkhhk() {
		return zqkhhk;
	}
	/**
	 * @return the xkxhhk
	 */
	public Double getXkxhhk() {
		return xkxhhk;
	}
	/**
	 * @return the jhwhk
	 */
	public Double getJhwhk() {
		return jhwhk;
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
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}
	/**
	 * @param yqyszksjhk the yqyszksjhk to set
	 */
	public void setYqyszksjhk(Double yqyszksjhk) {
		this.yqyszksjhk = yqyszksjhk;
	}
	/**
	 * @param yqksjhk the yqksjhk to set
	 */
	public void setYqksjhk(Double yqksjhk) {
		this.yqksjhk = yqksjhk;
	}
	/**
	 * @param wdqyszksjhk the wdqyszksjhk to set
	 */
	public void setWdqyszksjhk(Double wdqyszksjhk) {
		this.wdqyszksjhk = wdqyszksjhk;
	}
	/**
	 * @param wdqksjhk the wdqksjhk to set
	 */
	public void setWdqksjhk(Double wdqksjhk) {
		this.wdqksjhk = wdqksjhk;
	}
	/**
	 * @param qbkhhk the qbkhhk to set
	 */
	public void setQbkhhk(Double qbkhhk) {
		this.qbkhhk = qbkhhk;
	}
	/**
	 * @param zqkhhk the zqkhhk to set
	 */
	public void setZqkhhk(Double zqkhhk) {
		this.zqkhhk = zqkhhk;
	}
	/**
	 * @param xkxhhk the xkxhhk to set
	 */
	public void setXkxhhk(Double xkxhhk) {
		this.xkxhhk = xkxhhk;
	}
	/**
	 * @param jhwhk the jhwhk to set
	 */
	public void setJhwhk(Double jhwhk) {
		this.jhwhk = jhwhk;
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
