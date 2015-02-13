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
@Table(name = "jygk_ydjhzb")
public class YDJHZB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212069401L;

	private Integer dwid;

	private Integer zbid;

	private Integer nf;

	private Integer yf;

	private Double ydjhz;

	private Integer ydjhshzt;

	private Date ydjhxgsj;

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

	public Double getYdjhz() {
		return ydjhz;
	}

	public void setYdjhz(Double ydjhz) {
		this.ydjhz = ydjhz;
	}

	public Integer getYdjhshzt() {
		return ydjhshzt;
	}

	public void setYdjhshzt(Integer ydjhshzt) {
		this.ydjhshzt = ydjhshzt;
	}

	public Date getYdjhxgsj() {
		return ydjhxgsj;
	}

	public void setYdjhxgsj(Date ydjhxgsj) {
		this.ydjhxgsj = ydjhxgsj;
	}

}
