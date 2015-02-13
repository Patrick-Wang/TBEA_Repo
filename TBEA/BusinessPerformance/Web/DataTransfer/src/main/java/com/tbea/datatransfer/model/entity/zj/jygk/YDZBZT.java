package com.tbea.datatransfer.model.entity.zj.jygk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "jygk_ydzbzt")
public class YDZBZT extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1440655104212069401L;

	private Integer dwid;

	private Integer nf;

	private Integer yf;

	private Integer zt;

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

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

}
