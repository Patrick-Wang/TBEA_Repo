package com.tbea.datatransfer.model.entity.inner;

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
@Table(name = "yszk_zj_ydhkjhjgb")
public class HKJHJGB extends AbstractReadWriteEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String gsbm;

	private Double qbkhyqyszk;

	private Double qbkhyqk;

	private Double qbkhwdqyszk;

	private Double qbkhwdqk;

	private Double zqkhyqyszk;

	private Double zqkhyqk;

	private Double zqkhwdqyszk;

	private Double zqkhwdqk;

	private Double xyqsk;

	private Double gyqsk;

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

	public Double getQbkhyqyszk() {
		return qbkhyqyszk;
	}

	public void setQbkhyqyszk(Double qbkhyqyszk) {
		this.qbkhyqyszk = qbkhyqyszk;
	}

	public Double getQbkhyqk() {
		return qbkhyqk;
	}

	public void setQbkhyqk(Double qbkhyqk) {
		this.qbkhyqk = qbkhyqk;
	}

	public Double getQbkhwdqyszk() {
		return qbkhwdqyszk;
	}

	public void setQbkhwdqyszk(Double qbkhwdqyszk) {
		this.qbkhwdqyszk = qbkhwdqyszk;
	}

	public Double getQbkhwdqk() {
		return qbkhwdqk;
	}

	public void setQbkhwdqk(Double qbkhwdqk) {
		this.qbkhwdqk = qbkhwdqk;
	}

	public Double getZqkhyqyszk() {
		return zqkhyqyszk;
	}

	public void setZqkhyqyszk(Double zqkhyqyszk) {
		this.zqkhyqyszk = zqkhyqyszk;
	}

	public Double getZqkhyqk() {
		return zqkhyqk;
	}

	public void setZqkhyqk(Double zqkhyqk) {
		this.zqkhyqk = zqkhyqk;
	}

	public Double getZqkhwdqyszk() {
		return zqkhwdqyszk;
	}

	public void setZqkhwdqyszk(Double zqkhwdqyszk) {
		this.zqkhwdqyszk = zqkhwdqyszk;
	}

	public Double getZqkhwdqk() {
		return zqkhwdqk;
	}

	public void setZqkhwdqk(Double zqkhwdqk) {
		this.zqkhwdqk = zqkhwdqk;
	}

	public Double getXyqsk() {
		return xyqsk;
	}

	public void setXyqsk(Double xyqsk) {
		this.xyqsk = xyqsk;
	}

	public Double getGyqsk() {
		return gyqsk;
	}

	public void setGyqsk(Double gyqsk) {
		this.gyqsk = gyqsk;
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
		return "YDHKJHJGBLocal [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", qbkhyqyszk=" + qbkhyqyszk + ", qbkhyqk=" + qbkhyqk
				+ ", qbkhwdqyszk=" + qbkhwdqyszk + ", qbkhwdqk=" + qbkhwdqk
				+ ", zqkhyqyszk=" + zqkhyqyszk + ", zqkhyqk=" + zqkhyqk
				+ ", zqkhwdqyszk=" + zqkhwdqyszk + ", zqkhwdqk=" + zqkhwdqk
				+ ", xyqsk=" + xyqsk + ", gyqsk=" + gyqsk + ", sfdrwc="
				+ sfdrwc + ", qybh=" + qybh + "]";
	}

}
