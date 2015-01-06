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
@Table(name = "yszkjgqkb")
public class YSZKJG extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ny;

	private String hy;

	private Double ysje;

	private String zqbbl;

	private Double yq1yyn;

	private Double yq1_3y;

	private Double yq3_6y;

	private Double yq6_12y;

	private Double yq1nys;

	private Double wdqk;

	private Double wdqzbj;

	private Double yszkhj;

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

	public String getHy() {
		return hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public Double getYsje() {
		return ysje;
	}

	public void setYsje(Double ysje) {
		this.ysje = ysje;
	}

	public String getZqbbl() {
		return zqbbl;
	}

	public void setZqbbl(String zqbbl) {
		this.zqbbl = zqbbl;
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

	public Double getWdqk() {
		return wdqk;
	}

	public void setWdqk(Double wdqk) {
		this.wdqk = wdqk;
	}

	public Double getWdqzbj() {
		return wdqzbj;
	}

	public void setWdqzbj(Double wdqzbj) {
		this.wdqzbj = wdqzbj;
	}

	public Double getYszkhj() {
		return yszkhj;
	}

	public void setYszkhj(Double yszkhj) {
		this.yszkhj = yszkhj;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "YSZKJG [id=" + getId() + ", ny=" + ny + ", hy=" + hy
				+ ", ysje=" + ysje + ", zqbbl=" + zqbbl + ", yq1yyn=" + yq1yyn
				+ ", yq1_3y=" + yq1_3y + ", yq3_6y=" + yq3_6y + ", yq6_12y="
				+ yq6_12y + ", yq1nys=" + yq1nys + ", wdqk=" + wdqk
				+ ", wdqzbj=" + wdqzbj + ", yszkhj=" + yszkhj + ", qybh="
				+ qybh + "]";
	}

}