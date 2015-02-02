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
@Table(name = "jygk_ydzbdata")
public class YDZBData  extends AbstractReadWriteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5886120616781216571L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}
	
	private DWXX dwxx;
	
	private ZBXX zbxx;
	
	private String ny;
	
	private Double ydjhz;
	
	private Integer ydjhshzt;
	
	private Date ydjhxgsj;
	
	private Double firstyjz;
	
	private Integer firstyjshzt;
	
	private Date firstyjxgsj;
	
	private Double secondyjz;
	
	private Integer secondyjshzt;
	
	private Date secondyjxgsj;
	
	private Double sjz;
	
	private SHZT sjshzt; 
	
	private Date sjxgsj;

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
	 * @return the ny
	 */
	public String getNy() {
		return ny;
	}

	/**
	 * @return the ydjhz
	 */
	public Double getYdjhz() {
		return ydjhz;
	}

	/**
	 * @return the ydjhshzt
	 */
	public Integer getYdjhshzt() {
		return ydjhshzt;
	}

	/**
	 * @return the ydjhxgsj
	 */
	public Date getYdjhxgsj() {
		return ydjhxgsj;
	}

	@Column(name="[20yjz]")
	public Double getFirstyjz() {
		return firstyjz;
	}

	/**
	 * @param ny the ny to set
	 */
	public void setNy(String ny) {
		this.ny = ny;
	}

	/**
	 * @param ydjhz the ydjhz to set
	 */
	public void setYdjhz(Double ydjhz) {
		this.ydjhz = ydjhz;
	}

	/**
	 * @param ydjhshzt the ydjhshzt to set
	 */
	public void setYdjhshzt(Integer ydjhshzt) {
		this.ydjhshzt = ydjhshzt;
	}

	/**
	 * @param ydjhxgsj the ydjhxgsj to set
	 */
	public void setYdjhxgsj(Date ydjhxgsj) {
		this.ydjhxgsj = ydjhxgsj;
	}

	/**
	 * @param firstyjz the firstyjz to set
	 */
	public void setFirstyjz(Double firstyjz) {
		this.firstyjz = firstyjz;
	}

	@Column(name="[20yjshzt]")
	public Integer getFirstyjshzt() {
		return firstyjshzt;
	}

	public void setFirstyjshzt(Integer firstyjshzt) {
		this.firstyjshzt = firstyjshzt;
	}

	@Column(name="[20yjxgsj]")
	public Date getFirstyjxgsj() {
		return firstyjxgsj;
	}

	/**
	 * @param firstyjxgsj the firstyjxgsj to set
	 */
	public void setFirstyjxgsj(Date firstyjxgsj) {
		this.firstyjxgsj = firstyjxgsj;
	}

	@Column(name="[28yjz]")
	public Double getSecondyjz() {
		return secondyjz;
	}

	@Column(name="[28yjshzt]")
	public Integer getSecondyjshzt() {
		return secondyjshzt;
	}

	@Column(name="[28yjxgsj]")
	public Date getSecondyjxgsj() {
		return secondyjxgsj;
	}

	/**
	 * @param secondyjz the secondyjz to set
	 */
	public void setSecondyjz(Double secondyjz) {
		this.secondyjz = secondyjz;
	}

	/**
	 * @param secondyjshzt the secondyjshzt to set
	 */
	public void setSecondyjshzt(Integer secondyjshzt) {
		this.secondyjshzt = secondyjshzt;
	}

	/**
	 * @param secondyjxgsj the secondyjxgsj to set
	 */
	public void setSecondyjxgsj(Date secondyjxgsj) {
		this.secondyjxgsj = secondyjxgsj;
	}

	/**
	 * @return the sjz
	 */
	public Double getSjz() {
		return sjz;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "sjshzt")
	public SHZT getSjshzt() {
		return sjshzt;
	}

	/**
	 * @param sjz the sjz to set
	 */
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}

	/**
	 * @param sjshzt the sjshzt to set
	 */
	public void setSjshzt(SHZT sjshzt) {
		this.sjshzt = sjshzt;
	}

	/**
	 * @return the sjxgsj
	 */
	public Date getSjxgsj() {
		return sjxgsj;
	}

	/**
	 * @param sjxgsj the sjxgsj to set
	 */
	public void setSjxgsj(Date sjxgsj) {
		this.sjxgsj = sjxgsj;
	}
}
