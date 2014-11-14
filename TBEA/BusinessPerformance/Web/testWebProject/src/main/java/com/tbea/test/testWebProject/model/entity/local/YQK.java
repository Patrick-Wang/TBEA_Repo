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
@Table(name = "yqkqsbhb")
public class YQK extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ny;

	private Double yq1yyn;

	private Double yq1_3y;

	private Double yq3_6y;

	private Double yq6_12y;

	private Double yq1nys;

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

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public Double getYq1yyn() {
		return yq1yyn;
	}

	public void setYq1yyn(Double yq1yyn) {
		this.yq1yyn = yq1yyn;
	}

	public Double getYq1_3y() {
		return yq1_3y;
	}

	public void setYq1_3y(Double yq1_3y) {
		this.yq1_3y = yq1_3y;
	}

	public Double getYq3_6y() {
		return yq3_6y;
	}

	public void setYq3_6y(Double yq3_6y) {
		this.yq3_6y = yq3_6y;
	}

	public Double getYq6_12y() {
		return yq6_12y;
	}

	public void setYq6_12y(Double yq6_12y) {
		this.yq6_12y = yq6_12y;
	}

	public Double getYq1nys() {
		return yq1nys;
	}

	public void setYq1nys(Double yq1nys) {
		this.yq1nys = yq1nys;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "YQK [id=" + getId() + ", ny=" + ny + ", yq1yyn=" + yq1yyn
				+ ", yq1_3y=" + yq1_3y + ", yq3_6y=" + yq3_6y + ", yq6_12y="
				+ yq6_12y + ", yq1nys=" + yq1nys + ", qybh=" + qybh + "]";
	}

}