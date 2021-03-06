package com.tbea.datatransfer.model.entity.local;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszk_zj_mrhk")
public class MRHKLocal extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String xmgs;

	private String hkxz;

	private Date hkrq;

	private Double hkje;

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

	public String getXmgs() {
		return xmgs;
	}

	public void setXmgs(String xmgs) {
		this.xmgs = xmgs;
	}

	public String getHkxz() {
		return hkxz;
	}

	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}

	public Date getHkrq() {
		return hkrq;
	}

	public void setHkrq(Date hkrq) {
		this.hkrq = hkrq;
	}

	public Double getHkje() {
		return hkje;
	}

	public void setHkje(Double hkje) {
		this.hkje = hkje;
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
		return "MRHKLocal [id=" + getId() + ",gxrq=" + gxrq + ", xmgs=" + xmgs
				+ ", hkxz=" + hkxz + ", hkrq=" + hkrq + ", hkje=" + hkje
				+ ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
