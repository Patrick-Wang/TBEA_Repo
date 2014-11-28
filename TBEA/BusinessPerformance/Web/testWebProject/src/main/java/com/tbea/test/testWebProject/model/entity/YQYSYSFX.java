package com.tbea.test.testWebProject.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "Yqysysfx")
public class YQYSYSFX extends AbstractReadWriteEntity implements Serializable{
	
	private Integer Ysfl;
	private Integer Hs;
	private Double je;
	private Integer Flsdhs;
	private Double Flsdje;
	private Integer qybh;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	/**
	 * @return the ysfl
	 */
	public Integer getYsfl() {
		return Ysfl;
	}

	/**
	 * @return the hs
	 */
	public Integer getHs() {
		return Hs;
	}

	/**
	 * @return the je
	 */
	public Double getJe() {
		return je;
	}

	/**
	 * @return the flsdhs
	 */
	public Integer getFlsdhs() {
		return Flsdhs;
	}

	/**
	 * @return the flsdje
	 */
	public Double getFlsdje() {
		return Flsdje;
	}

	/**
	 * @param ysfl the ysfl to set
	 */
	public void setYsfl(Integer ysfl) {
		Ysfl = ysfl;
	}

	/**
	 * @param hs the hs to set
	 */
	public void setHs(Integer hs) {
		Hs = hs;
	}

	/**
	 * @param je the je to set
	 */
	public void setJe(Double je) {
		this.je = je;
	}

	/**
	 * @param flsdhs the flsdhs to set
	 */
	public void setFlsdhs(Integer flsdhs) {
		Flsdhs = flsdhs;
	}

	/**
	 * @param flsdje the flsdje to set
	 */
	public void setFlsdje(Double flsdje) {
		Flsdje = flsdje;
	}

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	
}
