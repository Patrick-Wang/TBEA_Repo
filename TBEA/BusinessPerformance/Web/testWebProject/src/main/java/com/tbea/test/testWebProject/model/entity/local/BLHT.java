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
@Table(name = "blhtdqqkhzb")
public class BLHT extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ny;

	private Double dqfkhfxsblye;

	private Integer dqfkhfxsblfs;

	private Double dqkhfxsblye;

	private Integer dqkhfxsblfs;

	private Double dqblje;

	private Integer dqblfs;

	private Double dqblzyhkje;

	private Integer dqblzyhkfs;

//	private Integer qybh;

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

	public Double getDqfkhfxsblye() {
		return dqfkhfxsblye;
	}

	public void setDqfkhfxsblye(Double dqfkhfxsblye) {
		this.dqfkhfxsblye = dqfkhfxsblye;
	}

	public Integer getDqfkhfxsblfs() {
		return dqfkhfxsblfs;
	}

	public void setDqfkhfxsblfs(Integer dqfkhfxsblfs) {
		this.dqfkhfxsblfs = dqfkhfxsblfs;
	}

	public Double getDqkhfxsblye() {
		return dqkhfxsblye;
	}

	public void setDqkhfxsblye(Double dqkhfxsblye) {
		this.dqkhfxsblye = dqkhfxsblye;
	}

	public Integer getDqkhfxsblfs() {
		return dqkhfxsblfs;
	}

	public void setDqkhfxsblfs(Integer dqkhfxsblfs) {
		this.dqkhfxsblfs = dqkhfxsblfs;
	}

	public Double getDqblje() {
		return dqblje;
	}

	public void setDqblje(Double dqblje) {
		this.dqblje = dqblje;
	}

	public Integer getDqblfs() {
		return dqblfs;
	}

	public void setDqblfs(Integer dqblfs) {
		this.dqblfs = dqblfs;
	}

	public Double getDqblzyhkje() {
		return dqblzyhkje;
	}

	public void setDqblzyhkje(Double dqblzyhkje) {
		this.dqblzyhkje = dqblzyhkje;
	}

	public Integer getDqblzyhkfs() {
		return dqblzyhkfs;
	}

	public void setDqblzyhkfs(Integer dqblzyhkfs) {
		this.dqblzyhkfs = dqblzyhkfs;
	}

//	public Integer getQybh() {
//		return qybh;
//	}
//
//	public void setQybh(Integer qybh) {
//		this.qybh = qybh;
//	}

	@Override
	public String toString() {
		return "BLHT [id=" + getId() + ", ny=" + ny + ", dqfkhfxsblye="
				+ dqfkhfxsblye + ", dqfkhfxsblfs=" + dqfkhfxsblfs
				+ ", dqkhfxsblye=" + dqkhfxsblye + ", dqkhfxsblfs="
				+ dqkhfxsblfs + ", dqblje=" + dqblje + ", dqblfs=" + dqblfs
				+ ", dqblzyhkje=" + dqblzyhkje + ", dqblzyhkfs=" + dqblzyhkfs
				/*+ ", qybh=" + qybh*/ + "]";
	}

}
