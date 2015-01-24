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
@Table(name = "cb_zj_xm")
public class XMXL extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String xmbh;

	private String xmmc;

	private String ddszdw;

	private String yhdwmc;

	private String khhylx;

	private Integer gb;

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

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getDdszdw() {
		return ddszdw;
	}

	public void setDdszdw(String ddszdw) {
		this.ddszdw = ddszdw;
	}

	public String getYhdwmc() {
		return yhdwmc;
	}

	public void setYhdwmc(String yhdwmc) {
		this.yhdwmc = yhdwmc;
	}

	public String getKhhylx() {
		return khhylx;
	}

	public void setKhhylx(String khhylx) {
		this.khhylx = khhylx;
	}

	public Integer getGb() {
		return gb;
	}

	public void setGb(Integer gb) {
		this.gb = gb;
	}

	@Override
	public String toString() {
		return "XMDL [id=" + getId() + ", gxrq=" + gxrq + ", xmbh=" + xmbh
				+ ", xmmc=" + xmmc + ", ddszdw=" + ddszdw + ", yhdwmc="
				+ yhdwmc + ", khhylx=" + khhylx + ", gb=" + gb + "]";
	}

}
