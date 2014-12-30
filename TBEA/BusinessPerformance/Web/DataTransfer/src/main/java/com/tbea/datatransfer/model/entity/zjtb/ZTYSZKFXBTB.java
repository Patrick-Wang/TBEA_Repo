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
@Table(name = "yszk_zj_ztyszkfxb")
public class ZTYSZKFXBTB extends AbstractReadOnlyEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String gsbm;

	private Double byzmyszkye;

	private Double byblkzye;

	private Double byyszksjs;

	private Double bysr;

	private Double qntqzmyszkye;

	private Double qntqblye;

	private Double qntqyszksjs;

	private Double qntqsr;

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

	public Double getByzmyszkye() {
		return byzmyszkye;
	}

	public void setByzmyszkye(Double byzmyszkye) {
		this.byzmyszkye = byzmyszkye;
	}

	public Double getByblkzye() {
		return byblkzye;
	}

	public void setByblkzye(Double byblkzye) {
		this.byblkzye = byblkzye;
	}

	public Double getByyszksjs() {
		return byyszksjs;
	}

	public void setByyszksjs(Double byyszksjs) {
		this.byyszksjs = byyszksjs;
	}

	public Double getBysr() {
		return bysr;
	}

	public void setBysr(Double bysr) {
		this.bysr = bysr;
	}

	public Double getQntqzmyszkye() {
		return qntqzmyszkye;
	}

	public void setQntqzmyszkye(Double qntqzmyszkye) {
		this.qntqzmyszkye = qntqzmyszkye;
	}

	public Double getQntqblye() {
		return qntqblye;
	}

	public void setQntqblye(Double qntqblye) {
		this.qntqblye = qntqblye;
	}

	public Double getQntqyszksjs() {
		return qntqyszksjs;
	}

	public void setQntqyszksjs(Double qntqyszksjs) {
		this.qntqyszksjs = qntqyszksjs;
	}

	public Double getQntqsr() {
		return qntqsr;
	}

	public void setQntqsr(Double qntqsr) {
		this.qntqsr = qntqsr;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "ZTYSZKFXBTB [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", byzmyszkye=" + byzmyszkye + ", byblkzye="
				+ byblkzye + ", byyszksjs=" + byyszksjs + ", bysr=" + bysr
				+ ", qntqzmyszkye=" + qntqzmyszkye + ", qntqblye=" + qntqblye
				+ ", qntqyszksjs=" + qntqyszksjs + ", qntqsr=" + qntqsr
				+ ", sfdrwc=" + sfdrwc + "]";
	}

}
