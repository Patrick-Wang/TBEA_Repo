package com.tbea.datatransfer.model.entity.zjdl;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "cb_zj_xlwg")
public class XLWGDL extends AbstractReadOnlyEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private Integer zxcpbh;

	private String wgsj;

	private Double cz;

	private Double djtyl;

	private Double djtdj;

	private Double tjgf;

	private Double lyl;

	private Double sjlvdj;

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

	public Integer getZxcpbh() {
		return zxcpbh;
	}

	public void setZxcpbh(Integer zxcpbh) {
		this.zxcpbh = zxcpbh;
	}

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

	@Override
	public String toString() {
		return "XLWGDL [id=" + getId() + ", gxrq=" + gxrq + ", zxcpbh="
				+ zxcpbh + ", wgsj=" + wgsj + ", cz=" + cz + ", djtyl=" + djtyl
				+ ", djtdj=" + djtdj + ", tjgf=" + tjgf + ", lyl=" + lyl
				+ ", sjlvdj=" + sjlvdj + ", qtcbhj=" + qtcbhj + ", yf=" + yf
				+ "]";
	}

}
