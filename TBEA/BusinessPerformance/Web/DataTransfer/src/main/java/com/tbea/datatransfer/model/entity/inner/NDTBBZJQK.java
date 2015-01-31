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
@Table(name = "ndtbbzjqk")
public class NDTBBZJQK extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nf;

	private Integer yf;

	private Double ye;

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

	public Double getYe() {
		return ye;
	}

	public void setYe(Double ye) {
		this.ye = ye;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "NDTBBZJQK [id=" + getId() + ", nf=" + nf + ", yf=" + yf
				+ ", ye=" + ye + ", qybh=" + qybh + "]";
	}

}
