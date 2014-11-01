package com.tbea.test.testWebProject.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cqk")
public class CQK extends AbstractReadWriteEntity implements Serializable {
	
	String Ny; // 年月
	String Hy;//	行业
	double Ln;//两年
	double Sn;//三年
	double Snjzq;//四年及之前
	double Zj;//总计

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name = "ny")
	public String getNy() {
		return Ny;
	}

	@Column(name = "hy", length=50)
	public String getHy() {
		return Hy;
	}

	@Column(name = "[2n]")
	public double getLn() {
		return Ln;
	}

	@Column(name = "[3n]")
	public double getSn() {
		return Sn;
	}

	@Column(name = "[4njzq]")
	public double getSnjzq() {
		return Snjzq;
	}

	@Column(name = "zj")
	public double getZj() {
		return Zj;
	}

	/**
	 * @param ny the ny to set
	 */
	public void setNy(String ny) {
		Ny = ny;
	}

	/**
	 * @param hy the hy to set
	 */
	public void setHy(String hy) {
		Hy = hy;
	}

	/**
	 * @param ln the ln to set
	 */
	public void setLn(double ln) {
		Ln = ln;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(double sn) {
		Sn = sn;
	}

	/**
	 * @param snjzq the snjzq to set
	 */
	public void setSnjzq(double snjzq) {
		Snjzq = snjzq;
	}

	/**
	 * @param zj the zj to set
	 */
	public void setZj(double zj) {
		Zj = zj;
	}
}
