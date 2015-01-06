package com.tbea.datatransfer.model.entity.inner;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "hkjhjgb")
public class HKJHJGB extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gsbm;

	private String ny;

	private Double qbkhyqyszk;

	private Double qbkhyqk;

	private Double qbkhwdqyszk;

	private Double qbkhwdqk;

	private Double zqkhyqyszk;

	private Double zqkhyqk;

	private Double zqkhwdqyszk;

	private Double zqkhwdqk;

	private Double byhlyqyszk;

	private Double byhlyqk;

	private Double byhlwdqyszk;

	private Double byhlwdqk;

	private Double byhlxj;

	private Double qbkhxj;

	private Double zqkhxj;

	private Double xyqsk;

	private Double gyqsk;

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

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
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

	public Double getByhlyqyszk() {
		return byhlyqyszk;
	}

	public void setByhlyqyszk(Double byhlyqyszk) {
		this.byhlyqyszk = byhlyqyszk;
	}

	public Double getByhlyqk() {
		return byhlyqk;
	}

	public void setByhlyqk(Double byhlyqk) {
		this.byhlyqk = byhlyqk;
	}

	public Double getByhlwdqyszk() {
		return byhlwdqyszk;
	}

	public void setByhlwdqyszk(Double byhlwdqyszk) {
		this.byhlwdqyszk = byhlwdqyszk;
	}

	public Double getByhlwdqk() {
		return byhlwdqk;
	}

	public void setByhlwdqk(Double byhlwdqk) {
		this.byhlwdqk = byhlwdqk;
	}

	public Double getByhlxj() {
		return byhlxj;
	}

	public void setByhlxj(Double byhlxj) {
		this.byhlxj = byhlxj;
	}

	public Double getQbkhxj() {
		return qbkhxj;
	}

	public void setQbkhxj(Double qbkhxj) {
		this.qbkhxj = qbkhxj;
	}

	public Double getZqkhxj() {
		return zqkhxj;
	}

	public void setZqkhxj(Double zqkhxj) {
		this.zqkhxj = zqkhxj;
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

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "HKJHJGB [id=" + getId() + ", gsbm=" + gsbm + ", ny=" + ny
				+ ", qbkhyqyszk=" + qbkhyqyszk + ", qbkhyqk=" + qbkhyqk
				+ ", qbkhwdqyszk=" + qbkhwdqyszk + ", qbkhwdqk=" + qbkhwdqk
				+ ", zqkhyqyszk=" + zqkhyqyszk + ", zqkhyqk=" + zqkhyqk
				+ ", zqkhwdqyszk=" + zqkhwdqyszk + ", zqkhwdqk=" + zqkhwdqk
				+ ", byhlyqyszk=" + byhlyqyszk + ", byhlyqk=" + byhlyqk
				+ ", byhlwdqyszk=" + byhlwdqyszk + ", byhlwdqk=" + byhlwdqk
				+ ", byhlxj=" + byhlxj + ", qbkhxj=" + qbkhxj + ", Zqkhxj="
				+ zqkhxj + ", xyqsk=" + xyqsk + ", gyqsk=" + gyqsk + ", qybh="
				+ qybh + "]";
	}

}
