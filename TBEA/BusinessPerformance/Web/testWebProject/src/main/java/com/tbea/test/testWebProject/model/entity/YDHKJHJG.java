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
@Table(name = "hkjhjgb")
public class YDHKJHJG  extends AbstractReadWriteEntity {
	String gsbh;
	String Ny;
	Double Qbkhyqys;
	Double Qbkhyqk;
	Double Qbkhwdqys;
	Double Qbkhwdqk;
	Double Zqkhyqys;
	Double Zqkhyqk;
	Double Zqkhwdqys;
	Double Zqkhwdqk;
	Double Byjlyqys;
	Double Byhlyqk;
	Double Byhlwdqys;
	Double Byhlwdqk;
	Double Byhlxj;
	Double Qbkhxj;
	Double Zqkhxj;
	Double Xyqsk;
	Double Gyqsk;
	Integer qybh;
	/**
	 * @return the iD
	 */
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
	 * @return the gsbh
	 */
	public String getGsbh() {
		return gsbh;
	}

	/**
	 * @return the ny
	 */
	public String getNy() {
		return Ny;
	}

	/**
	 * @return the qbkhyqys
	 */
	public Double getQbkhyqys() {
		return Qbkhyqys;
	}

	/**
	 * @return the qbkhyqk
	 */
	public Double getQbkhyqk() {
		return Qbkhyqk;
	}

	/**
	 * @return the qbkhwdqys
	 */
	public Double getQbkhwdqys() {
		return Qbkhwdqys;
	}

	/**
	 * @return the qbkhwdqk
	 */
	public Double getQbkhwdqk() {
		return Qbkhwdqk;
	}

	/**
	 * @return the zqkhyqys
	 */
	public Double getZqkhyqys() {
		return Zqkhyqys;
	}

	/**
	 * @return the zqkhyqk
	 */
	public Double getZqkhyqk() {
		return Zqkhyqk;
	}

	/**
	 * @return the zqkhwdqys
	 */
	public Double getZqkhwdqys() {
		return Zqkhwdqys;
	}

	/**
	 * @return the zqkhwdqk
	 */
	public Double getZqkhwdqk() {
		return Zqkhwdqk;
	}

	/**
	 * @return the byjlyqys
	 */
	public Double getByjlyqys() {
		return Byjlyqys;
	}

	/**
	 * @return the byhlyqk
	 */
	public Double getByhlyqk() {
		return Byhlyqk;
	}

	/**
	 * @return the byhlwdqys
	 */
	public Double getByhlwdqys() {
		return Byhlwdqys;
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
	 * @param gsbh the gsbh to set
	 */
	public void setGsbh(String gsbh) {
		this.gsbh = gsbh;
	}

	/**
	 * @param ny the ny to set
	 */
	public void setNy(String ny) {
		Ny = ny;
	}

	/**
	 * @param qbkhyqys the qbkhyqys to set
	 */
	public void setQbkhyqys(Double qbkhyqys) {
		Qbkhyqys = qbkhyqys;
	}

	/**
	 * @param qbkhyqk the qbkhyqk to set
	 */
	public void setQbkhyqk(Double qbkhyqk) {
		Qbkhyqk = qbkhyqk;
	}

	/**
	 * @param qbkhwdqys the qbkhwdqys to set
	 */
	public void setQbkhwdqys(Double qbkhwdqys) {
		Qbkhwdqys = qbkhwdqys;
	}

	/**
	 * @param qbkhwdqk the qbkhwdqk to set
	 */
	public void setQbkhwdqk(Double qbkhwdqk) {
		Qbkhwdqk = qbkhwdqk;
	}

	/**
	 * @param zqkhyqys the zqkhyqys to set
	 */
	public void setZqkhyqys(Double zqkhyqys) {
		Zqkhyqys = zqkhyqys;
	}

	/**
	 * @param zqkhyqk the zqkhyqk to set
	 */
	public void setZqkhyqk(Double zqkhyqk) {
		Zqkhyqk = zqkhyqk;
	}

	/**
	 * @param zqkhwdqys the zqkhwdqys to set
	 */
	public void setZqkhwdqys(Double zqkhwdqys) {
		Zqkhwdqys = zqkhwdqys;
	}

	/**
	 * @param zqkhwdqk the zqkhwdqk to set
	 */
	public void setZqkhwdqk(Double zqkhwdqk) {
		Zqkhwdqk = zqkhwdqk;
	}

	/**
	 * @param byjlyqys the byjlyqys to set
	 */
	public void setByjlyqys(Double byjlyqys) {
		Byjlyqys = byjlyqys;
	}

	/**
	 * @param byhlyqk the byhlyqk to set
	 */
	public void setByhlyqk(Double byhlyqk) {
		Byhlyqk = byhlyqk;
	}

	/**
	 * @param byhlwdqys the byhlwdqys to set
	 */
	public void setByhlwdqys(Double byhlwdqys) {
		Byhlwdqys = byhlwdqys;
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
