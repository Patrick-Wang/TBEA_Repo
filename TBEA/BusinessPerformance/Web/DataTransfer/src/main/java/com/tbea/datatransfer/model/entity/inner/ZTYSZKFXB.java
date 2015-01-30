package com.tbea.datatransfer.model.entity.inner;

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
@Table(name = "ztyszkfxb")
public class ZTYSZKFXB extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String gsbm;

	private Double byzmyszkye;

	private Double byblkzye;

	private Double byyszksjs;

	private Double ljsr;

	private String zmyszsrb;

	private Double qntqzmyszkye;

	private Double qntqblye;

	private Double qntqyszksjs;

	private Double qntqsr;

	private String qntqzmyszsrb;

	private String zmyejqntqzzb;

	private String bljqntqzzb;

	private String sjysjqntqzzb;

	private String srjqntqzzb;

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

	public Double getLjsr() {
		return ljsr;
	}

	public void setLjsr(Double ljsr) {
		this.ljsr = ljsr;
	}

	public String getZmyszsrb() {
		return zmyszsrb;
	}

	public void setZmyszsrb(String zmyszsrb) {
		this.zmyszsrb = zmyszsrb;
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

	public String getQntqzmyszsrb() {
		return qntqzmyszsrb;
	}

	public void setQntqzmyszsrb(String qntqzmyszsrb) {
		this.qntqzmyszsrb = qntqzmyszsrb;
	}

	public String getZmyejqntqzzb() {
		return zmyejqntqzzb;
	}

	public void setZmyejqntqzzb(String zmyejqntqzzb) {
		this.zmyejqntqzzb = zmyejqntqzzb;
	}

	public String getBljqntqzzb() {
		return bljqntqzzb;
	}

	public void setBljqntqzzb(String bljqntqzzb) {
		this.bljqntqzzb = bljqntqzzb;
	}

	public String getSjysjqntqzzb() {
		return sjysjqntqzzb;
	}

	public void setSjysjqntqzzb(String sjysjqntqzzb) {
		this.sjysjqntqzzb = sjysjqntqzzb;
	}

	public String getSrjqntqzzb() {
		return srjqntqzzb;
	}

	public void setSrjqntqzzb(String srjqntqzzb) {
		this.srjqntqzzb = srjqntqzzb;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "ZTYSZKFXB [id=" + getId() + ", gxrq=" + gxrq + ", gsbm=" + gsbm
				+ ", byzmyszkye=" + byzmyszkye + ", byblkzye=" + byblkzye
				+ ", byyszksjs=" + byyszksjs + ", ljsr=" + ljsr + ", zmyszsrb="
				+ zmyszsrb + ", qntqzmyszkye=" + qntqzmyszkye + ", qntqblye="
				+ qntqblye + ", qntqyszksjs=" + qntqyszksjs + ", qntqsr="
				+ qntqsr + ", qntqzmyszsrb=" + qntqzmyszsrb + ", zmyejqntqzzb="
				+ zmyejqntqzzb + ", bljqntqzzb=" + bljqntqzzb
				+ ", sjysjqntqzzb=" + sjysjqntqzzb + ", srjqntqzzb="
				+ srjqntqzzb + ", qybh=" + qybh + "]";
	}

}