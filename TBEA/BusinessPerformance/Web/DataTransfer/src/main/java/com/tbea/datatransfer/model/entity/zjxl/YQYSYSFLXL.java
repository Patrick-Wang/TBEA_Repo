package com.tbea.datatransfer.model.entity.zjxl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_yqysysfl")
public class YQYSYSFLXL extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nf;

	private String yf;

	private String ysflmc;

	private Integer zhs;

	private Double zje;

	private Integer flqshs;

	private Double flqsje;

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

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getYsflmc() {
		return ysflmc;
	}

	public void setYsflmc(String ysflmc) {
		this.ysflmc = ysflmc;
	}

	public Integer getZhs() {
		return zhs;
	}

	public void setZhs(Integer zhs) {
		this.zhs = zhs;
	}

	public Double getZje() {
		return zje;
	}

	public void setZje(Double zje) {
		this.zje = zje;
	}

	public Integer getFlqshs() {
		return flqshs;
	}

	public void setFlqshs(Integer flqshs) {
		this.flqshs = flqshs;
	}

	public Double getFlqsje() {
		return flqsje;
	}

	public void setFlqsje(Double flqsje) {
		this.flqsje = flqsje;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

}