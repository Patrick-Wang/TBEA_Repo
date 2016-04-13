package com.tbea.ic.operation.model.entity.yszkgb;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.SHZT;


@Entity
@Table(name = "yszkgb_yszkzmyyjtztjqs")
public class YszkYjtzTjqsEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer nf;
	Integer yf;
	DWXX dwxx;
	Double cwzmysjsye;
	Double blye;
	Double hfpwkje;
	Double pkhwfje;
	Double yskcjys;
	Double xyzcjys;
	Double qtyskmyx;
	Double yjtzyszkye;
	SHZT zt;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}
	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}
	public Double getCwzmysjsye() {
		return cwzmysjsye;
	}
	public void setCwzmysjsye(Double cwzmysjsye) {
		this.cwzmysjsye = cwzmysjsye;
	}
	public Double getBlye() {
		return blye;
	}
	public void setBlye(Double blye) {
		this.blye = blye;
	}
	public Double getHfpwkje() {
		return hfpwkje;
	}
	public void setHfpwkje(Double hfpwkje) {
		this.hfpwkje = hfpwkje;
	}
	public Double getPkhwfje() {
		return pkhwfje;
	}
	public void setPkhwfje(Double pkhwfje) {
		this.pkhwfje = pkhwfje;
	}
	public Double getYskcjys() {
		return yskcjys;
	}
	public void setYskcjys(Double yskcjys) {
		this.yskcjys = yskcjys;
	}
	public Double getXyzcjys() {
		return xyzcjys;
	}
	public void setXyzcjys(Double xyzcjys) {
		this.xyzcjys = xyzcjys;
	}
	public Double getQtyskmyx() {
		return qtyskmyx;
	}
	public void setQtyskmyx(Double qtyskmyx) {
		this.qtyskmyx = qtyskmyx;
	}
	public Double getYjtzyszkye() {
		return yjtzyszkye;
	}
	public void setYjtzyszkye(Double yjtzyszkye) {
		this.yjtzyszkye = yjtzyszkye;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zt")
	public SHZT getZt() {
		return zt;
	}
	public void setZt(SHZT zt) {
		this.zt = zt;
	}
	

}
