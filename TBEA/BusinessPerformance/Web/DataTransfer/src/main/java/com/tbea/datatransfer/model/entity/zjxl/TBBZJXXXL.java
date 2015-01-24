package com.tbea.datatransfer.model.entity.zjxl;

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
@Table(name = "yszk_zj_tbbzjxx")
public class TBBZJXXXL extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String gsbm;

	private Integer nf;

	private Integer yf;

	private Double je;

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

	public Integer getNf() {
		return nf;
	}

	public void setNf(Integer nf) {
		this.nf = nf;
	}

	public Integer getYf() {
		return yf;
	}

	public void setYf(Integer yf) {
		this.yf = yf;
	}

	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "TBBZJXXDL [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", nf=" + nf + ", yf=" + yf + ", je=" + je
				+ ", sfdrwc=" + sfdrwc + "]";
	}

}
