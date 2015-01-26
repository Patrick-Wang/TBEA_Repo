package com.tbea.datatransfer.model.entity.local;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszk_zj_ydsjhkqk")
public class YDSJHKQKLocal extends AbstractReadWriteEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String gsbm;

	private Double yqyszksjhk;

	private Double yqksjhk;

	private Double wdqyszksjhk;

	private Double wdqksjhk;

	private Double qbkhhk;

	private Double zqkhhk;

	private Double xkxhhk;

	private Double jhwhk;

	private String sfdrwc;

	private Integer qybh;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Date getGxrq() {
		return gxrq;
	}

	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public Double getYqyszksjhk() {
		return null == yqyszksjhk ? 0.0D : yqyszksjhk;
	}

	public void setYqyszksjhk(Double yqyszksjhk) {
		this.yqyszksjhk = yqyszksjhk;
	}

	public Double getYqksjhk() {
		return null == yqksjhk ? 0.0D : yqksjhk;
	}

	public void setYqksjhk(Double yqksjhk) {
		this.yqksjhk = yqksjhk;
	}

	public Double getWdqyszksjhk() {
		return null == wdqyszksjhk ? 0.0D : wdqyszksjhk;
	}

	public void setWdqyszksjhk(Double wdqyszksjhk) {
		this.wdqyszksjhk = wdqyszksjhk;
	}

	public Double getWdqksjhk() {
		return null == wdqksjhk ? 0.0D : wdqksjhk;
	}

	public void setWdqksjhk(Double wdqksjhk) {
		this.wdqksjhk = wdqksjhk;
	}

	public Double getQbkhhk() {
		return null == qbkhhk ? 0.0D : qbkhhk;
	}

	public void setQbkhhk(Double qbkhhk) {
		this.qbkhhk = qbkhhk;
	}

	public Double getZqkhhk() {
		return null == zqkhhk ? 0.0D : zqkhhk;
	}

	public void setZqkhhk(Double zqkhhk) {
		this.zqkhhk = zqkhhk;
	}

	public Double getXkxhhk() {
		return null == xkxhhk ? 0.0D : xkxhhk;
	}

	public void setXkxhhk(Double xkxhhk) {
		this.xkxhhk = xkxhhk;
	}

	public Double getJhwhk() {
		return null == jhwhk ? 0.0D : jhwhk;
	}

	public void setJhwhk(Double jhwhk) {
		this.jhwhk = jhwhk;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "YDSJHKQKLocal [id=" + getId() + ", gxrq=" + gxrq
				+ ", gsbm=" + gsbm + ", yqyszksjhk=" + yqyszksjhk
				+ ", yqksjhk=" + yqksjhk + ", wdqyszksjhk=" + wdqyszksjhk
				+ ", wdqksjhk=" + wdqksjhk + ", qbkhhk=" + qbkhhk + ", zqkhhk="
				+ zqkhhk + ", xkxhhk=" + xkxhhk + ", jhwhk=" + jhwhk
				+ ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
