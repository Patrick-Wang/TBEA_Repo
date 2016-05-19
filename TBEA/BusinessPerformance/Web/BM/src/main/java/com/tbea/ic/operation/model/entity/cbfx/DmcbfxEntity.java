package com.tbea.ic.operation.model.entity.cbfx;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cbfx_dmcbmx")
public class DmcbfxEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer dwid;
	Integer cbflid;
	Double jhz;
	Double sjz;
	Integer zt;
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
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	public Integer getCbflid() {
		return cbflid;
	}
	public void setCbflid(Integer cbflid) {
		this.cbflid = cbflid;
	}
	public Double getJhz() {
		return jhz;
	}
	public void setJhz(Double jhz) {
		this.jhz = jhz;
	}
	public Double getSjz() {
		return sjz;
	}
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	

}
