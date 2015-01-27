package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tbea.ic.operation.common.Util;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszkjgqkb")
public class YSZKJGQK extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	String Ny;//	年月
	String Hy;//	行业
	Double Ysje;//	应收金额
	String Zqbbl;//	占全部比例
	Double Yyn;//	逾期1个月以内
	Double Ydsy;//	逾期1-3月
	Double Sdly;//	逾期3-6月
	Double Ldsey;//	逾期6-12月
	Double Ynys;//	逾期1年以上
	Double Wdqk;//	未到期款
	Double Wdqzbj;//	未到期质保金
	Double Yszkhj;//	应收账款合计
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
	
	@Column(name = "ny")
	public String getNy() {
		return Ny;
	}

	@Column(name = "hy")
	public String getHy() {
		return Hy;
	}

	@Column(name = "ysje")
	public Double getYsje() {
		return Util.valueOf(Ysje);
	}

	@Column(name = "zqbbl")
	public String getZqbbl() {
		return Zqbbl;
	}

	@Column(name = "yq1yyn")
	public Double getYyn() {
		return Util.valueOf(Yyn);
	}

	@Column(name = "yq1_3y")
	public Double getYdsy() {
		return Util.valueOf(Ydsy);
	}

	@Column(name = "yq3_6y")
	public Double getSdly() {
		return Util.valueOf(Sdly);
	}

	@Column(name = "yq6_12y")
	public Double getLdsey() {
		return Util.valueOf(Ldsey);
	}

	@Column(name = "[yq1nys]")
	public Double getYnys() {
		return Util.valueOf(Ynys);
	}

	@Column(name = "wdqk")
	public Double getWdqk() {
		return Util.valueOf(Wdqk);
	}

	@Column(name = "wdqzbj")
	public Double getWdqzbj() {
		return Util.valueOf(Wdqzbj);
	}

	@Column(name = "yszkhj")
	public Double getYszkhj() {
		return Util.valueOf(Yszkhj);
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
	 * @param ysje the ysje to set
	 */
	public void setYsje(Double ysje) {
		Ysje = ysje;
	}

	/**
	 * @param zqbbl the zqbbl to set
	 */
	public void setZqbbl(String zqbbl) {
		Zqbbl = zqbbl;
	}

	/**
	 * @param yyn the yyn to set
	 */
	public void setYyn(Double yyn) {
		Yyn = yyn;
	}

	/**
	 * @param ydsy the ydsy to set
	 */
	public void setYdsy(Double ydsy) {
		Ydsy = ydsy;
	}

	/**
	 * @param sdly the sdly to set
	 */
	public void setSdly(Double sdly) {
		Sdly = sdly;
	}

	/**
	 * @param ldsey the ldsey to set
	 */
	public void setLdsey(Double ldsey) {
		Ldsey = ldsey;
	}

	/**
	 * @param ynys the ynys to set
	 */
	public void setYnys(Double ynys) {
		Ynys = ynys;
	}

	/**
	 * @param wdqk the wdqk to set
	 */
	public void setWdqk(Double wdqk) {
		Wdqk = wdqk;
	}

	/**
	 * @param wdqzbj the wdqzbj to set
	 */
	public void setWdqzbj(Double wdqzbj) {
		Wdqzbj = wdqzbj;
	}

	/**
	 * @param yszkhj the yszkhj to set
	 */
	public void setYszkhj(Double yszkhj) {
		Yszkhj = yszkhj;
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

}
