package com.tbea.datatransfer.model.entity.local;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cb_zj_xlwg")
public class XLWGLocal extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private Integer zxcpbh;

//	private String dwmc;

	private String wgsj;

	private Double cz;

	private Double djtyl;

	private Double djtdj;

	private Double tjgf;

	private Double lyl;

	private Double sjlvdj;

	private Double qtcbhj;

	private Double yf;

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

	public Date getGxrq() {
		return gxrq;
	}

	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	public Integer getZxcpbh() {
		return zxcpbh;
	}

	public void setZxcpbh(Integer zxcpbh) {
		this.zxcpbh = zxcpbh;
	}

//	public String getDwmc() {
//		return dwmc;
//	}
//
//	public void setDwmc(String dwmc) {
//		this.dwmc = dwmc;
//	}

	public String getWgsj() {
		return wgsj;
	}

	public void setWgsj(String wgsj) {
		this.wgsj = wgsj;
	}

	public Double getCz() {
		return cz;
	}

	public void setCz(Double cz) {
		this.cz = cz;
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

	public Double getTjgf() {
		return tjgf;
	}

	public void setTjgf(Double tjgf) {
		this.tjgf = tjgf;
	}

	public Double getLyl() {
		return lyl;
	}

	public void setLyl(Double lyl) {
		this.lyl = lyl;
	}

	public Double getSjlvdj() {
		return sjlvdj;
	}

	public void setSjlvdj(Double sjlvdj) {
		this.sjlvdj = sjlvdj;
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

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "XLWGLocal [id=" + getId() + ", gxrq=" + gxrq + ", zxcpbh="
				+ zxcpbh/* + ", dwmc=" + dwmc */ + ", wgsj=" + wgsj + ", cz=" + cz
				+ ", djtyl=" + djtyl + ", djtdj=" + djtdj + ", tjgf=" + tjgf
				+ ", lyl=" + lyl + ", sjlvdj=" + sjlvdj + ", qtcbhj=" + qtcbhj
				+ ", yf=" + yf + ", qybh=" + qybh + "]";
	}

}
