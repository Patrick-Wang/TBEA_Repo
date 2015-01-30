package com.tbea.datatransfer.model.entity.yszk15;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_bl")
public class BL15 extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String blbh;

	private String htbh;

	private Date blrq;

	private Double kxxz;

	private Double blje;

	private Date bldqr;

	private Date kxdqr;

	private Double blhkje;

	private Double blye;

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

	public String getBlbh() {
		return blbh;
	}

	public void setBlbh(String blbh) {
		this.blbh = blbh;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public Date getBlrq() {
		return blrq;
	}

	public void setBlrq(Date blrq) {
		this.blrq = blrq;
	}

	public Double getKxxz() {
		return kxxz;
	}

	public void setKxxz(Double kxxz) {
		this.kxxz = kxxz;
	}

	public Double getBlje() {
		return blje;
	}

	public void setBlje(Double blje) {
		this.blje = blje;
	}

	public Date getBldqr() {
		return bldqr;
	}

	public void setBldqr(Date bldqr) {
		this.bldqr = bldqr;
	}

	public Date getKxdqr() {
		return kxdqr;
	}

	public void setKxdqr(Date kxdqr) {
		this.kxdqr = kxdqr;
	}

	public Double getBlhkje() {
		return blhkje;
	}

	public void setBlhkje(Double blhkje) {
		this.blhkje = blhkje;
	}

	public Double getBlye() {
		return blye;
	}

	public void setBlye(Double blye) {
		this.blye = blye;
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
		return "BL15 [id=" + getId() + ", gxrq=" + gxrq + ", blbh=" + blbh + ", htbh=" + htbh
				+ ", blrq=" + blrq + ", kxxz=" + kxxz + ", blje=" + blje
				+ ", bldqr=" + bldqr + ", kxdqr=" + kxdqr + ", blhkje="
				+ blhkje + ", blye=" + blye + ", sfdrwc=" + sfdrwc + ", qybh="
				+ qybh + "]";
	}

}
