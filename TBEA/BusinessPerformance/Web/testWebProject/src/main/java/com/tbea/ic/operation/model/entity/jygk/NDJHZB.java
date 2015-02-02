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
@Table(name = "jygk_ndjhzb")
public class NDJHZB  extends AbstractReadWriteEntity implements Serializable{

	private static final long serialVersionUID = 1440655104212069401L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	
	private DWXX dwxx;
	
	private ZBXX zbxx;
	
	private Double ndjhz;
	
	private SHZT ndjhshzt;
	
	private Date ndjhxgsj;
	
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
	 * @param dwxx the dwxx to set
	 */
	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	/**
	 * @param zbxx the zbxx to set
	 */
	public void setZbxx(ZBXX zbxx) {
		this.zbxx = zbxx;
	}

	/**
	 * @return the ndjhz
	 */
	public Double getNdjhz() {
		return ndjhz;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ndjhshzt")
	public SHZT getNdjhshzt() {
		return ndjhshzt;
	}

	/**
	 * @return the ndjhxgsj
	 */
	public Date getNdjhxgsj() {
		return ndjhxgsj;
	}

	/**
	 * @param ndjhz the ndjhz to set
	 */
	public void setNdjhz(Double ndjhz) {
		this.ndjhz = ndjhz;
	}

	/**
	 * @param ndjhshzt the ndjhshzt to set
	 */
	public void setNdjhshzt(SHZT ndjhshzt) {
		this.ndjhshzt = ndjhshzt;
	}

	/**
	 * @param ndjhxgsj the ndjhxgsj to set
	 */
	public void setNdjhxgsj(Date ndjhxgsj) {
		this.ndjhxgsj = ndjhxgsj;
	}
}
