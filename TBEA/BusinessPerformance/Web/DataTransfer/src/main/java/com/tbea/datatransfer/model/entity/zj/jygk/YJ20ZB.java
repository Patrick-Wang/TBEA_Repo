package com.tbea.datatransfer.model.entity.zj.jygk;

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
@Table(name = "jygk_yj20zb")
public class YJ20ZB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212069401L;

	private Integer dwid;

	private Integer zbid;

	private Integer nf;

	private Integer yf;

	private Double yj20z;

	private Integer yj20shzt;

	private Date yj20xgsj;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Integer getDwid() {
		return dwid;
	}

	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}

	public Integer getZbid() {
		return zbid;
	}

	public void setZbid(Integer zbid) {
		this.zbid = zbid;
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

	public Double getYj20z() {
		return yj20z;
	}

	public void setYj20z(Double yj20z) {
		this.yj20z = yj20z;
	}

	public Integer getYj20shzt() {
		return yj20shzt;
	}

	public void setYj20shzt(Integer yj20shzt) {
		this.yj20shzt = yj20shzt;
	}

	public Date getYj20xgsj() {
		return yj20xgsj;
	}

	public void setYj20xgsj(Date yj20xgsj) {
		this.yj20xgsj = yj20xgsj;
	}

}
