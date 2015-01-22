package com.tbea.datatransfer.model.entity.inner;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yqysysfx")
public class YQYSYSFX extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ysfl;

	private Integer hs;

	private Double je;

	private Integer flsdhs;

	private Double flsdje;

	private Integer qybh;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Integer getYsfl() {
		return ysfl;
	}

	public void setYsfl(Integer ysfl) {
		this.ysfl = ysfl;
	}

	public Integer getHs() {
		return hs;
	}

	public void setHs(Integer hs) {
		this.hs = hs;
	}

	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}

	public Integer getFlsdhs() {
		return flsdhs;
	}

	public void setFlsdhs(Integer flsdhs) {
		this.flsdhs = flsdhs;
	}

	public Double getFlsdje() {
		return flsdje;
	}

	public void setFlsdje(Double flsdje) {
		this.flsdje = flsdje;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "YQYSYSFX [id=" + getId() + ", ysfl=" + ysfl + ", hs=" + hs
				+ ", je=" + je + ", flsdhs=" + flsdhs + ", flsdje=" + flsdje
				+ ", qybh=" + qybh + "]";
	}

}