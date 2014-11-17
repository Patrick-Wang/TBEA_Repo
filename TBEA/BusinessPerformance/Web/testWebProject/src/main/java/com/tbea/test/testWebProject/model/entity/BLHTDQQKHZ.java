package com.tbea.test.testWebProject.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tbea.test.testWebProject.common.Util;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "blhtdqqkhzb")
public class BLHTDQQKHZ extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	String Ny; // 年月
	Double Dqfkhfxsblye; //	到期非客户付息式保理余额
	Integer Dqfkhfxsblfs; //	到期非客户付息式保理份数
	Double Dqkhfxsblye; //	到期客户付息式保理余额
	Integer Dqkhfxsblfs; //	到期客户付息式保理份数
	Double Dqblje; //	到期保理金额
	Integer Dqblfs; //	到期保理份数
	Double Dqblzyhkje; //	到期保理中已回款金额
	Integer Dqblzyhkfs; //	到期保理中已回款份数
	Integer qybh;

	/**
	 * @return the qybh
	 */
	@Column(name = "qybh")	
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

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

	@Column(name = "dqfkhfxsblye")
	public Double getDqfkhfxsblye() {
		return Util.valueOf(Dqfkhfxsblye);
	}

	@Column(name = "dqfkhfxsblfs")
	public Integer getDqfkhfxsblfs() {
		return Util.valueOf(Dqfkhfxsblfs);
	}

	@Column(name = "dqkhfxsblye")
	public Double getDqkhfxsblye() {
		return Util.valueOf(Dqkhfxsblye);
	}

	@Column(name = "dqkhfxsblfs")
	public Integer getDqkhfxsblfs() {
		return Util.valueOf(Dqkhfxsblfs);
	}

	@Column(name = "dqblje")
	public Double getDqblje() {
		return Util.valueOf(Dqblje);
	}

	@Column(name = "dqblfs")
	public Integer getDqblfs() {
		return Util.valueOf(Dqblfs);
	}

	@Column(name = "dqblzyhkje")
	public Double getDqblzyhkje() {
		return Util.valueOf(Dqblzyhkje);
	}

	@Column(name = "dqblzyhkfs")
	public Integer getDqblzyhkfs() {
		return Util.valueOf(Dqblzyhkfs);
	}

	/**
	 * @param ny the ny to set
	 */
	public void setNy(String ny) {
		Ny = ny;
	}

	/**
	 * @param dqfkhfxsblye the dqfkhfxsblye to set
	 */
	public void setDqfkhfxsblye(Double dqfkhfxsblye) {
		Dqfkhfxsblye = dqfkhfxsblye;
	}

	/**
	 * @param dqfkhfxsblfs the dqfkhfxsblfs to set
	 */
	public void setDqfkhfxsblfs(Integer dqfkhfxsblfs) {
		Dqfkhfxsblfs = dqfkhfxsblfs;
	}

	/**
	 * @param dqkhfxsblye the dqkhfxsblye to set
	 */
	public void setDqkhfxsblye(Double dqkhfxsblye) {
		Dqkhfxsblye = dqkhfxsblye;
	}

	/**
	 * @param dqkhfxsblfs the dqkhfxsblfs to set
	 */
	public void setDqkhfxsblfs(Integer dqkhfxsblfs) {
		Dqkhfxsblfs = dqkhfxsblfs;
	}

	/**
	 * @param dqblje the dqblje to set
	 */
	public void setDqblje(Double dqblje) {
		Dqblje = dqblje;
	}

	/**
	 * @param dqblfs the dqblfs to set
	 */
	public void setDqblfs(Integer dqblfs) {
		Dqblfs = dqblfs;
	}

	/**
	 * @param dqblzyhkje the dqblzyhkje to set
	 */
	public void setDqblzyhkje(Double dqblzyhkje) {
		Dqblzyhkje = dqblzyhkje;
	}

	/**
	 * @param dqblzyhkfs the dqblzyhkfs to set
	 */
	public void setDqblzyhkfs(Integer dqblzyhkfs) {
		Dqblzyhkfs = dqblzyhkfs;
	}





}
