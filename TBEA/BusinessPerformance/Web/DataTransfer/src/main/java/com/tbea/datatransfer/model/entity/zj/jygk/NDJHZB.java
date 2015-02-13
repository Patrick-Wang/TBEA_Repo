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
@Table(name = "jygk_ndjhzb")
public class NDJHZB extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212069401L;

	private Integer dwid;

	private Integer zbid;

	private Integer nf;

	private Double ndjhz;

	private Integer ndjhshzt;

	private Date ndjhxgsj;

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

	public Double getNdjhz() {
		return ndjhz;
	}

	public void setNdjhz(Double ndjhz) {
		this.ndjhz = ndjhz;
	}

	public Integer getNdjhshzt() {
		return ndjhshzt;
	}

	public void setNdjhshzt(Integer ndjhshzt) {
		this.ndjhshzt = ndjhshzt;
	}

	public Date getNdjhxgsj() {
		return ndjhxgsj;
	}

	public void setNdjhxgsj(Date ndjhxgsj) {
		this.ndjhxgsj = ndjhxgsj;
	}

}
