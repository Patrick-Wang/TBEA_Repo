package com.tbea.datatransfer.model.entity.zjxl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "cb_zj_xltb")
public class XLTBXL extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String xmbh;

	private Date tbbjsj;

	private Integer cpdl;

	private Double xlsl;

	private Double cz;

	private String yjkbsj;

	private Double yczbgl;

	private Double djtyl;

	private Double djtdj;

	private Double lyl;

	private Double tblvdj;

	private Double qtcbhj;

	private Double yf;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Date getGxrq() {
		return gxrq;
	}

	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public Date getTbbjsj() {
		return tbbjsj;
	}

	public void setTbbjsj(Date tbbjsj) {
		this.tbbjsj = tbbjsj;
	}

	public Integer getCpdl() {
		return cpdl;
	}

	public void setCpdl(Integer cpdl) {
		this.cpdl = cpdl;
	}

	public Double getXlsl() {
		return xlsl;
	}

	public void setXlsl(Double xlsl) {
		this.xlsl = xlsl;
	}

	public Double getCz() {
		return cz;
	}

	public void setCz(Double cz) {
		this.cz = cz;
	}

	public String getYjkbsj() {
		return yjkbsj;
	}

	public void setYjkbsj(String yjkbsj) {
		this.yjkbsj = yjkbsj;
	}

	public Double getYczbgl() {
		return yczbgl;
	}

	public void setYczbgl(Double yczbgl) {
		this.yczbgl = yczbgl;
	}

	public Double getDjtyl() {
		return djtyl;
	}

	public void setDjtyl(Double djtyl) {
		this.djtyl = djtyl;
	}

	public Double getDjtdj() {
		return djtdj;
	}

	public void setDjtdj(Double djtdj) {
		this.djtdj = djtdj;
	}

	public Double getLyl() {
		return lyl;
	}

	public void setLyl(Double lyl) {
		this.lyl = lyl;
	}

	public Double getTblvdj() {
		return tblvdj;
	}

	public void setTblvdj(Double tblvdj) {
		this.tblvdj = tblvdj;
	}

	public Double getQtcbhj() {
		return qtcbhj;
	}

	public void setQtcbhj(Double qtcbhj) {
		this.qtcbhj = qtcbhj;
	}

	public Double getYf() {
		return yf;
	}

	public void setYf(Double yf) {
		this.yf = yf;
	}

	@Override
	public String toString() {
		return "XLTBDL [id=" + getId() + ", gxrq=" + gxrq + ", xmbh=" + xmbh
				+ ", tbbjsj=" + tbbjsj + ", cpdl=" + cpdl + ", xlsl=" + xlsl
				+ ", cz=" + cz + ", yjkbsj=" + yjkbsj + ", yczbgl=" + yczbgl
				+ ", djtyl=" + djtyl + ", djtdj=" + djtdj + ", lyl=" + lyl
				+ ", tblvdj=" + tblvdj + ", qtcbhj=" + qtcbhj + ", yf=" + yf
				+ "]";
	}

}
