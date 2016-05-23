package com.tbea.ic.operation.model.entity.dzwzgb;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "dzwgb_ylfx_dzclkcb")
public class DzclkcbEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer clid;
	Integer dwid;
	Double qhyk;
	Double scxhyjj;
	Double cgyjj;
	Double sxfybbj;
	Double mblrdsj;
	Double cgl;
	Double zdjazbbj;
	Double zdjazmulr;
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
	public Integer getClid() {
		return clid;
	}
	public void setClid(Integer clid) {
		this.clid = clid;
	}
	public Double getQhyk() {
		return qhyk;
	}
	public void setQhyk(Double qhyk) {
		this.qhyk = qhyk;
	}
	public Double getScxhyjj() {
		return scxhyjj;
	}
	public void setScxhyjj(Double scxhyjj) {
		this.scxhyjj = scxhyjj;
	}
	public Double getCgyjj() {
		return cgyjj;
	}
	public void setCgyjj(Double cgyjj) {
		this.cgyjj = cgyjj;
	}
	public Double getSxfybbj() {
		return sxfybbj;
	}
	public void setSxfybbj(Double sxfybbj) {
		this.sxfybbj = sxfybbj;
	}
	public Double getMblrdsj() {
		return mblrdsj;
	}
	public void setMblrdsj(Double mblrdsj) {
		this.mblrdsj = mblrdsj;
	}
	public Double getCgl() {
		return cgl;
	}
	public void setCgl(Double cgl) {
		this.cgl = cgl;
	}
	public Double getZdjazbbj() {
		return zdjazbbj;
	}
	public void setZdjazbbj(Double zdjazbbj) {
		this.zdjazbbj = zdjazbbj;
	}
	public Double getZdjazmulr() {
		return zdjazmulr;
	}
	public void setZdjazmulr(Double zdjazmulr) {
		this.zdjazmulr = zdjazmulr;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}

}
