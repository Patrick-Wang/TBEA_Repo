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
@Table(name = "jygk_sjzb")
public class SJZB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212069401L;

	private Integer dwid;

	private Integer zbid;

	private Integer nf;

	private Integer yf;

	private Double sjz;

	private Integer sjshzt;

	private Date sjxgsj;

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

	public Double getSjz() {
		return sjz;
	}

	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}

	public Integer getSjshzt() {
		return sjshzt;
	}

	public void setSjshzt(Integer sjshzt) {
		this.sjshzt = sjshzt;
	}

	public Date getSjxgsj() {
		return sjxgsj;
	}

	public void setSjxgsj(Date sjxgsj) {
		this.sjxgsj = sjxgsj;
	}

}
