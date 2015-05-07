package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "jygk_yj28zb")
public class YJ28ZB extends AbstractReadWriteEntity implements Serializable{

	private static final long serialVersionUID = 1440655104212869401L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	private DWXX dwxx;

	private ZBXX zbxx;

	private Integer nf;

	private Integer yf;

	private Double yj28z;

	private SHZT yj28shzt;

	private Date yj28xgsj;
	
	private Date yj28shsj;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zbid")
	public ZBXX getZbxx() {
		return zbxx;
	}

	/**
	 * @param dwxx
	 *            the dwxx to set
	 */
	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	/**
	 * @param zbxx
	 *            the zbxx to set
	 */
	public void setZbxx(ZBXX zbxx) {
		this.zbxx = zbxx;
	}

	/**
	 * @return the yj28z
	 */
	public Double getYj28z() {
		return yj28z;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "yj28shzt")
	public SHZT getYj28shzt() {
		return yj28shzt;
	}

	/**
	 * @return the yj28xgsj
	 */
	public Date getYj28xgsj() {
		return yj28xgsj;
	}

	/**
	 * @param yj28z
	 *            the yj28z to set
	 */
	public void setYj28z(Double yj28z) {
		this.yj28z = yj28z;
	}

	/**
	 * @param yj28shzt
	 *            the yj28shzt to set
	 */
	public void setYj28shzt(SHZT yj28shzt) {
		this.yj28shzt = yj28shzt;
	}

	/**
	 * @param yj28xgsj
	 *            the yj28xgsj to set
	 */
	public void setYj28xgsj(Date yj28xgsj) {
		this.yj28xgsj = yj28xgsj;
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

	public Date getYj28shsj() {
		return yj28shsj;
	}

	public void setYj28shsj(Date yj28shsj) {
		this.yj28shsj = yj28shsj;
	}

}
