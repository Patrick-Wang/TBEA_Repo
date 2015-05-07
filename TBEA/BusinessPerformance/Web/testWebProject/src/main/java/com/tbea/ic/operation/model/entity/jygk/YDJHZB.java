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
@Table(name = "jygk_ydjhzb")
public class YDJHZB extends AbstractReadWriteEntity implements Serializable {

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

	private Double ydjhz;

	private SHZT ydjhshzt;

	private Date ydjhxgsj;
	
	private Date ydjhshsj;

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
	 * @return the ydjhz
	 */
	public Double getYdjhz() {
		return ydjhz;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ydjhshzt")
	public SHZT getYdjhshzt() {
		return ydjhshzt;
	}

	/**
	 * @return the ydjhxgsj
	 */
	public Date getYdjhxgsj() {
		return ydjhxgsj;
	}

	/**
	 * @param ydjhz
	 *            the ydjhz to set
	 */
	public void setYdjhz(Double ydjhz) {
		this.ydjhz = ydjhz;
	}

	/**
	 * @param ydjhshzt
	 *            the ydjhshzt to set
	 */
	public void setYdjhshzt(SHZT ydjhshzt) {
		this.ydjhshzt = ydjhshzt;
	}

	/**
	 * @param ydjhxgsj
	 *            the ydjhxgsj to set
	 */
	public void setYdjhxgsj(Date ydjhxgsj) {
		this.ydjhxgsj = ydjhxgsj;
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

	public Date getYdjhshsj() {
		return ydjhshsj;
	}

	public void setYdjhshsj(Date ydjhshsj) {
		this.ydjhshsj = ydjhshsj;
	}

}
