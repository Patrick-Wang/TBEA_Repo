package com.tbea.datatransfer.model.entity.zjtb;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_ydsjhkqk")
public class YDSJHKQKTB extends AbstractReadOnlyEntity implements Serializable {

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
		return yqyszksjhk;
	}

	public void setYqyszksjhk(Double yqyszksjhk) {
		this.yqyszksjhk = yqyszksjhk;
	}

	public Double getYqksjhk() {
		return yqksjhk;
	}

	public void setYqksjhk(Double yqksjhk) {
		this.yqksjhk = yqksjhk;
	}

	public Double getWdqyszksjhk() {
		return wdqyszksjhk;
	}

	public void setWdqyszksjhk(Double wdqyszksjhk) {
		this.wdqyszksjhk = wdqyszksjhk;
	}

	public Double getWdqksjhk() {
		return wdqksjhk;
	}

	public void setWdqksjhk(Double wdqksjhk) {
		this.wdqksjhk = wdqksjhk;
	}

	public Double getQbkhhk() {
		return qbkhhk;
	}

	public void setQbkhhk(Double qbkhhk) {
		this.qbkhhk = qbkhhk;
	}

	public Double getZqkhhk() {
		return zqkhhk;
	}

	public void setZqkhhk(Double zqkhhk) {
		this.zqkhhk = zqkhhk;
	}

	public Double getXkxhhk() {
		return xkxhhk;
	}

	public void setXkxhhk(Double xkxhhk) {
		this.xkxhhk = xkxhhk;
	}

	public Double getJhwhk() {
		return jhwhk;
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

	@Override
	public String toString() {
		return "YDSJHKQKTB [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", yqyszksjhk=" + yqyszksjhk + ", yqksjhk=" + yqksjhk
				+ ", wdqyszksjhk=" + wdqyszksjhk + ", wdqksjhk=" + wdqksjhk
				+ ", qbkhhk=" + qbkhhk + ", zqkhhk=" + zqkhhk + ", xkxhhk="
				+ xkxhhk + ", jhwhk=" + jhwhk + ", sfdrwc=" + sfdrwc + "]";
	}

}
