package com.tbea.ic.operation.model.entity;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "jygk_ysdaily_new")
public class Yszkrb extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// 录入年份
	@Column(name = "[lrnf]")
	Integer lrnf;
	// 录入月份
	@Column(name = "[lryf]")
	Integer lryf;
	// 录入日期
	@Column(name = "[lrrq]")
	Integer lrrq;
	// 单位ID
	@Column(name = "[dwID]")
	Integer dwID;
	// 应收账款指标
	@Column(name = "[yszkzb]")
	Double yszkzb;
	// 回款计划
	@Column(name = "[hkjh]")
	Double hkjh;
	// 其中：确保款项
	@Column(name = "[qbkx]")
	Double qbkx;
	// 争取款项
	@Column(name = "[zqkx]")
	Double zqkx;
	// 上月应收余额
	@Column(name = "[syysye]")
	Double syysye;
	// 今日新增应收账款
	@Column(name = "[jrxzyszk]")
	Double jrxzyszk;
	// 今日回款
	@Column(name = "[jrhk]")
	Double jrhk;
	// 累计可降应收回款
	@Column(name = "[ljkjyshk]")
	Double ljkjyshk;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	public void setId(int id) {
		super.setId(id);
	}

	public Double getHkjh() {
		return hkjh;
	}
	public void setHkjh(double hkjh) {
		this.hkjh = hkjh;
	}

	public Double getJrxzyszk() {
		return jrxzyszk;
	}
	public void setJrxzyszk(double jrxzyszk) {
		this.jrxzyszk = jrxzyszk;
	}

	public Double getJrhk() {
		return jrhk;
	}
	public void setJrhk(double jrhk) {
		this.jrhk = jrhk;
	}

	public Double getLjkjyshk() {
		return ljkjyshk;
	}
	public void setLjkjyshk(double ljkjyshk) {
		this.ljkjyshk = ljkjyshk;
	}

	public Double getQbkx() {
		return qbkx;
	}
	public void setQbkx(double qbkx) {
		this.qbkx = qbkx;
	}

	public Double getSyysye() {
		return syysye;
	}
	public void setSyysye(double syysye) {
		this.syysye = syysye;
	}

	public Double getYszkzb() {
		return yszkzb;
	}
	public void setYszkzb(double yszkzb) {
		this.yszkzb = yszkzb;
	}

	public Double getZqkx() {
		return zqkx;
	}
	public void setZqkx(double zqkx) {
		this.zqkx = zqkx;
	}

	public Integer getDwID() {
		return dwID;
	}
	public void setDwID(int dwID) {
		this.dwID = dwID;
	}

	public Integer getLrnf() {
		return lrnf;
	}
	public void setLrnf(int lrnf) {
		this.lrnf = lrnf;
	}

	public Integer getLrrq() {
		return lrrq;
	}
	public void setLrrq(int lrrq) {
		this.lrrq = lrrq;
	}

	public Integer getLryf() {
		return lryf;
	}
	public void setLryf(int lryf) {
		this.lryf = lryf;
	}
}
