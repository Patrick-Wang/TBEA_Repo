package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;


public class XJL {
	private String drlr;
	private String drlc;
	private String drjll;
	private String dylr;
	private String dylc;
	private String dyjll;
	private String dnlr;
	private String dnlc;
	private String dnjll;
	private String bytzs;
	/**
	 * @return the drlr
	 */
	public String getDrlr() {
		return drlr;
	}

	/**
	 * @return the drlc
	 */
	public String getDrlc() {
		return drlc;
	}
	/**
	 * @return the drjll
	 */
	public String getDrjll() {
		return drjll;
	}
	/**
	 * @return the dylr
	 */
	public String getDylr() {
		return dylr;
	}
	/**
	 * @return the dylc
	 */
	public String getDylc() {
		return dylc;
	}
	/**
	 * @return the dyjll
	 */
	public String getDyjll() {
		return dyjll;
	}
	/**
	 * @return the dnlr
	 */
	public String getDnlr() {
		return dnlr;
	}
	/**
	 * @return the dnlc
	 */
	public String getDnlc() {
		return dnlc;
	}
	/**
	 * @return the dnjll
	 */
	public String getDnjll() {
		return dnjll;
	}
	/**
	 * @return the bytzs
	 */
	public String getBytzs() {
		return bytzs;
	}
	/**
	 * @param drlr the drlr to set
	 */
	public void setDrlr(String drlr) {
		this.drlr = drlr;
	}
	/**
	 * @param drlc the drlc to set
	 */
	public void setDrlc(String drlc) {
		this.drlc = drlc;
	}
	/**
	 * @param drjll the drjll to set
	 */
	public void setDrjll(String drjll) {
		this.drjll = drjll;
	}
	/**
	 * @param dylr the dylr to set
	 */
	public void setDylr(String dylr) {
		this.dylr = dylr;
	}
	/**
	 * @param dylc the dylc to set
	 */
	public void setDylc(String dylc) {
		this.dylc = dylc;
	}
	/**
	 * @param dyjll the dyjll to set
	 */
	public void setDyjll(String dyjll) {
		this.dyjll = dyjll;
	}
	/**
	 * @param dnlr the dnlr to set
	 */
	public void setDnlr(String dnlr) {
		this.dnlr = dnlr;
	}
	/**
	 * @param dnlc the dnlc to set
	 */
	public void setDnlc(String dnlc) {
		this.dnlc = dnlc;
	}
	/**
	 * @param dnjll the dnjll to set
	 */
	public void setDnjll(String dnjll) {
		this.dnjll = dnjll;
	}
	/**
	 * @param bytzs the bytzs to set
	 */
	public void setBytzs(String bytzs) {
		this.bytzs = bytzs;
	}
}
