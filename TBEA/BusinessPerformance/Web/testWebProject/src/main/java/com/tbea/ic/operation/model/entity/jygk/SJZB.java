package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
@Table(name = "jygk_sjzb")
public class SJZB extends AbstractReadWriteEntity implements Serializable {

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

	private Double sjz;

	private SHZT sjshzt;

	private Timestamp sjxgsj;
	
	private Timestamp sjshsj;

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
	 * @return the sjz
	 */
	public Double getSjz() {
		return sjz;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sjshzt")
	public SHZT getSjshzt() {
		return sjshzt;
	}

	/**
	 * @return the sjxgsj
	 */
	public Timestamp getSjxgsj() {
		return sjxgsj;
	}

	/**
	 * @param sjz
	 *            the sjz to set
	 */
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}

	/**
	 * @param sjshzt
	 *            the sjshzt to set
	 */
	public void setSjshzt(SHZT sjshzt) {
		this.sjshzt = sjshzt;
	}

	/**
	 * @param sjxgsj
	 *            the sjxgsj to set
	 */
	public void setSjxgsj(Timestamp sjxgsj) {
		this.sjxgsj = sjxgsj;
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

	public Timestamp getSjshsj() {
		return sjshsj;
	}

	public void setSjshsj(Timestamp sjshsj) {
		this.sjshsj = sjshsj;
	}

}
