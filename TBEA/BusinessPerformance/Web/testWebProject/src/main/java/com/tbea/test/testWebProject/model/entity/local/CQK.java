package com.tbea.test.testWebProject.model.entity.local;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cqk")
public class CQK extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ny;

	private String hy;

	private Double cqk2n;

	private Double cqk3n;

	private Double cqk4njzq;

	private Double zj;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public String getHy() {
		return hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	@Column(name = "[2n]")
	public Double getCqk2n() {
		return cqk2n;
	}

	public void setCqk2n(Double cqk2n) {
		this.cqk2n = cqk2n;
	}

	@Column(name = "[3n]")
	public Double getCqk3n() {
		return cqk3n;
	}

	public void setCqk3n(Double cqk3n) {
		this.cqk3n = cqk3n;
	}

	@Column(name = "[4njzq]")
	public Double getCqk4njzq() {
		return cqk4njzq;
	}

	public void setCqk4njzq(Double cqk4njzq) {
		this.cqk4njzq = cqk4njzq;
	}

	public Double getZj() {
		return zj;
	}

	public void setZj(Double zj) {
		this.zj = zj;
	}

	@Override
	public String toString() {
		return "CQK [id=" + getId() + ", ny=" + ny + ", hy=" + hy + ", cqk2n="
				+ cqk2n + ", cqk3n=" + cqk3n + ", cqk4njzq=" + cqk4njzq
				+ ", zj=" + zj + "]";
	}

}
