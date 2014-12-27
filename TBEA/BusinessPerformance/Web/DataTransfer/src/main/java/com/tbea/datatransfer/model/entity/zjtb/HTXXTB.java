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
@Table(name = "yszk_zj_htxx")
public class HTXXTB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String htbh;

	private String xmxx;

	private String sspq;

	private String khbh;

	private String khmc;

	private String khsshy;

	private Date qdrq;

	private Double cpje;

	private Double fy;

	private Double zje;

	private String htzt;

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

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getXmxx() {
		return xmxx;
	}

	public void setXmxx(String xmxx) {
		this.xmxx = xmxx;
	}

	public String getSspq() {
		return sspq;
	}

	public void setSspq(String sspq) {
		this.sspq = sspq;
	}

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKhsshy() {
		return khsshy;
	}

	public void setKhsshy(String khsshy) {
		this.khsshy = khsshy;
	}

	public Date getQdrq() {
		return qdrq;
	}

	public void setQdrq(Date qdrq) {
		this.qdrq = qdrq;
	}

	public Double getCpje() {
		return cpje;
	}

	public void setCpje(Double cpje) {
		this.cpje = cpje;
	}

	public Double getFy() {
		return fy;
	}

	public void setFy(Double fy) {
		this.fy = fy;
	}

	public Double getZje() {
		return zje;
	}

	public void setZje(Double zje) {
		this.zje = zje;
	}

	public String getHtzt() {
		return htzt;
	}

	public void setHtzt(String htzt) {
		this.htzt = htzt;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "HTXXLocal [id=" + getId() + ", gxrq=" + gxrq + ", htbh=" + htbh
				+ ", xmxx=" + xmxx + ", sspq=" + sspq + ", khbh=" + khbh
				+ ", khmc=" + khmc + ", khsshy=" + khsshy + ", qdrq=" + qdrq
				+ ", cpje=" + cpje + ", fy=" + fy + ", zje=" + zje + ", htzt="
				+ htzt + ", sfdrwc=" + sfdrwc + "]";
	}

}
