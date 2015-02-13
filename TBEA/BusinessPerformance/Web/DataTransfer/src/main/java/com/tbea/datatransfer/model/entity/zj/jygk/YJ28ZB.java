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
@Table(name = "jygk_yj28zb")
public class YJ28ZB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212869401L;

	private Integer dwid;

	private Integer zbid;

	private Integer nf;

	private Integer yf;

	private Double yj28z;

	private Integer yj28shzt;

	private Date yj28xgsj;

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

	public Double getYj28z() {
		return yj28z;
	}

	public void setYj28z(Double yj28z) {
		this.yj28z = yj28z;
	}

	public Integer getYj28shzt() {
		return yj28shzt;
	}

	public void setYj28shzt(Integer yj28shzt) {
		this.yj28shzt = yj28shzt;
	}

	public Date getYj28xgsj() {
		return yj28xgsj;
	}

	public void setYj28xgsj(Date yj28xgsj) {
		this.yj28xgsj = yj28xgsj;
	}

}
