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
@Table(name = "jygk_yj20zb")
public class YJ20ZB extends AbstractReadWriteEntity implements Serializable{

	private static final long serialVersionUID = 1440655104212069401L;

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

	private Double yj20z;

	private SHZT yj20shzt;

	private Date yj20xgsj;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
	 * @return the yj20z
	 */
	public Double getYj20z() {
		return yj20z;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "yj20shzt")
	public SHZT getYj20shzt() {
		return yj20shzt;
	}

	/**
	 * @return the yj20xgsj
	 */
	public Date getYj20xgsj() {
		return yj20xgsj;
	}

	/**
	 * @param yj20z
	 *            the yj20z to set
	 */
	public void setYj20z(Double yj20z) {
		this.yj20z = yj20z;
	}

	/**
	 * @param yj20shzt
	 *            the yj20shzt to set
	 */
	public void setYj20shzt(SHZT yj20shzt) {
		this.yj20shzt = yj20shzt;
	}

	/**
	 * @param yj20xgsj
	 *            the yj20xgsj to set
	 */
	public void setYj20xgsj(Date yj20xgsj) {
		this.yj20xgsj = yj20xgsj;
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


}
