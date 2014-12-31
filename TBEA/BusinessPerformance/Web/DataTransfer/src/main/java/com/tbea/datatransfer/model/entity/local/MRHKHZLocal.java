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
@Table(name = "yszk_zj_mrhkhz")
public class MRHKHZLocal extends AbstractReadWriteEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private Date hkrq;

	private Double hkje;

	private Double qzqbbc;

	private Double qzzqbc;

	private Double yhkzkjysdhkje;

	private Double jzydyszkzmye;

	private Double jtxdydzjhlzb;

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

	public Double getQzqbbc() {
		return qzqbbc;
	}

	public void setQzqbbc(Double qzqbbc) {
		this.qzqbbc = qzqbbc;
	}

	public Double getQzzqbc() {
		return qzzqbc;
	}

	public void setQzzqbc(Double qzzqbc) {
		this.qzzqbc = qzzqbc;
	}

	public Double getYhkzkjysdhkje() {
		return yhkzkjysdhkje;
	}

	public void setYhkzkjysdhkje(Double yhkzkjysdhkje) {
		this.yhkzkjysdhkje = yhkzkjysdhkje;
	}

	public Double getJzydyszkzmye() {
		return jzydyszkzmye;
	}

	public void setJzydyszkzmye(Double jzydyszkzmye) {
		this.jzydyszkzmye = jzydyszkzmye;
	}

	public Double getJtxdydzjhlzb() {
		return jtxdydzjhlzb;
	}

	public void setJtxdydzjhlzb(Double jtxdydzjhlzb) {
		this.jtxdydzjhlzb = jtxdydzjhlzb;
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
		return "MRHKHZLocal [id=" + getId() + ", gxrq=" + gxrq + ", hkrq="
				+ hkrq + ", hkje=" + hkje + ", qzqbbc=" + qzqbbc + ", qzzqbc="
				+ qzzqbc + ", yhkzkjysdhkje=" + yhkzkjysdhkje
				+ ", jzydyszkzmye=" + jzydyszkzmye + ", jtxdydzjhlzb="
				+ jtxdydzjhlzb + ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
