package com.tbea.ic.operation.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hkjhjgb")
public class HKJHJG {
	Integer	id	;
	String	gsbh	;
	String	ny	;
	Double	Qbkhyqyszk	;
	Double	Qbkhyqk	;
	Double	Qbkhwdqyszk	;
	Double	Qbkhwdqk	;
	Double	Zqkhyqyszk	;
	Double	Zqkhyqk	;
	Double	Zqkhwdqyszk	;
	Double	Zqkhwdqk	;
	Double	Byhlyqyszk	;
	Double	Byhlyqk	;
	Double	Byhlwdqyszk	;
	Double	Byhlwdqk	;
	Double	Byhlxj	;
	Double	Qbkhxj	;
	Double	Zqkhxj	;
	Double	Xyqsk	;
	Double	Gyqsk	;
	Integer	qybh	;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}


	/**
	 * @return the gsbh
	 */
	@Column(name = "gsbm")
	public String getGsbh() {
		return gsbh;
	}


	/**
	 * @return the ny
	 */
	public String getNy() {
		return ny;
	}


	/**
	 * @return the qbkhyqyszk
	 */
	public Double getQbkhyqyszk() {
		return Qbkhyqyszk;
	}


	/**
	 * @return the qbkhyqk
	 */
	public Double getQbkhyqk() {
		return Qbkhyqk;
	}


	/**
	 * @return the qbkhwdqyszk
	 */
	public Double getQbkhwdqyszk() {
		return Qbkhwdqyszk;
	}


	/**
	 * @return the qbkhwdqk
	 */
	public Double getQbkhwdqk() {
		return Qbkhwdqk;
	}


	/**
	 * @return the zqkhyqyszk
	 */
	public Double getZqkhyqyszk() {
		return Zqkhyqyszk;
	}


	/**
	 * @return the zqkhyqk
	 */
	public Double getZqkhyqk() {
		return Zqkhyqk;
	}


	/**
	 * @return the zqkhwdqyszk
	 */
	public Double getZqkhwdqyszk() {
		return Zqkhwdqyszk;
	}


	/**
	 * @return the zqkhwdqk
	 */
	public Double getZqkhwdqk() {
		return Zqkhwdqk;
	}


	/**
	 * @return the byhlyqyszk
	 */
	public Double getByhlyqyszk() {
		return Byhlyqyszk;
	}


	/**
	 * @return the byhlyqk
	 */
	public Double getByhlyqk() {
		return Byhlyqk;
	}


	/**
	 * @return the byhlwdqyszk
	 */
	public Double getByhlwdqyszk() {
		return Byhlwdqyszk;
	}


	/**
	 * @return the byhlwdqk
	 */
	public Double getByhlwdqk() {
		return Byhlwdqk;
	}


	/**
	 * @return the byhlxj
	 */
	public Double getByhlxj() {
		return Byhlxj;
	}


	/**
	 * @return the qbkhxj
	 */
	public Double getQbkhxj() {
		return Qbkhxj;
	}


	/**
	 * @return the zqkhxj
	 */
	public Double getZqkhxj() {
		return Zqkhxj;
	}


	/**
	 * @return the xyqsk
	 */
	public Double getXyqsk() {
		return Xyqsk;
	}


	/**
	 * @return the gyqsk
	 */
	public Double getGyqsk() {
		return Gyqsk;
	}


	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @param gsbh the gsbh to set
	 */
	public void setGsbh(String gsbh) {
		this.gsbh = gsbh;
	}


	/**
	 * @param ny the ny to set
	 */
	public void setNy(String ny) {
		this.ny = ny;
	}


	/**
	 * @param qbkhyqyszk the qbkhyqyszk to set
	 */
	public void setQbkhyqyszk(Double qbkhyqyszk) {
		Qbkhyqyszk = qbkhyqyszk;
	}


	/**
	 * @param qbkhyqk the qbkhyqk to set
	 */
	public void setQbkhyqk(Double qbkhyqk) {
		Qbkhyqk = qbkhyqk;
	}


	/**
	 * @param qbkhwdqyszk the qbkhwdqyszk to set
	 */
	public void setQbkhwdqyszk(Double qbkhwdqyszk) {
		Qbkhwdqyszk = qbkhwdqyszk;
	}


	/**
	 * @param qbkhwdqk the qbkhwdqk to set
	 */
	public void setQbkhwdqk(Double qbkhwdqk) {
		Qbkhwdqk = qbkhwdqk;
	}


	/**
	 * @param zqkhyqyszk the zqkhyqyszk to set
	 */
	public void setZqkhyqyszk(Double zqkhyqyszk) {
		Zqkhyqyszk = zqkhyqyszk;
	}


	/**
	 * @param zqkhyqk the zqkhyqk to set
	 */
	public void setZqkhyqk(Double zqkhyqk) {
		Zqkhyqk = zqkhyqk;
	}


	/**
	 * @param zqkhwdqyszk the zqkhwdqyszk to set
	 */
	public void setZqkhwdqyszk(Double zqkhwdqyszk) {
		Zqkhwdqyszk = zqkhwdqyszk;
	}


	/**
	 * @param zqkhwdqk the zqkhwdqk to set
	 */
	public void setZqkhwdqk(Double zqkhwdqk) {
		Zqkhwdqk = zqkhwdqk;
	}


	/**
	 * @param byhlyqyszk the byhlyqyszk to set
	 */
	public void setByhlyqyszk(Double byhlyqyszk) {
		Byhlyqyszk = byhlyqyszk;
	}


	/**
	 * @param byhlyqk the byhlyqk to set
	 */
	public void setByhlyqk(Double byhlyqk) {
		Byhlyqk = byhlyqk;
	}


	/**
	 * @param byhlwdqyszk the byhlwdqyszk to set
	 */
	public void setByhlwdqyszk(Double byhlwdqyszk) {
		Byhlwdqyszk = byhlwdqyszk;
	}


	/**
	 * @param byhlwdqk the byhlwdqk to set
	 */
	public void setByhlwdqk(Double byhlwdqk) {
		Byhlwdqk = byhlwdqk;
	}


	/**
	 * @param byhlxj the byhlxj to set
	 */
	public void setByhlxj(Double byhlxj) {
		Byhlxj = byhlxj;
	}


	/**
	 * @param qbkhxj the qbkhxj to set
	 */
	public void setQbkhxj(Double qbkhxj) {
		Qbkhxj = qbkhxj;
	}


	/**
	 * @param zqkhxj the zqkhxj to set
	 */
	public void setZqkhxj(Double zqkhxj) {
		Zqkhxj = zqkhxj;
	}


	/**
	 * @param xyqsk the xyqsk to set
	 */
	public void setXyqsk(Double xyqsk) {
		Xyqsk = xyqsk;
	}


	/**
	 * @param gyqsk the gyqsk to set
	 */
	public void setGyqsk(Double gyqsk) {
		Gyqsk = gyqsk;
	}


	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}
	
}
