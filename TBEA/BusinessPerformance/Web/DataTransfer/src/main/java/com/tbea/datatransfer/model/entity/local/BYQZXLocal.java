package com.tbea.datatransfer.model.entity.local;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cb_zj_byqzx")
public class BYQZXLocal extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private Integer tbcpbh;

	private Integer ddzxjd;

	private String hth;

	private Date htzbsj;

	private Date jhsj;

	private String gzh;

	private Double cz;

	private String ggph;

	private Double ggyl;

	private Double ggdj;

	private Double djtyl;

	private Double djtdj;

	private Double tjgf;

	private String byqygg;

	private Double byqyyl;

	private Double byqydj;

	private Double gcyl;

	private Double gcdj;

	private Double zbyl;

	private Double zbdj;

	private Double qtclcb;

	private Double rgjzzfy;

	private Double yf;

	private Integer qybh;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public Integer getTbcpbh() {
		return tbcpbh;
	}

	public void setTbcpbh(Integer tbcpbh) {
		this.tbcpbh = tbcpbh;
	}

	public Integer getDdzxjd() {
		return ddzxjd;
	}

	public void setDdzxjd(Integer ddzxjd) {
		this.ddzxjd = ddzxjd;
	}

	public String getHth() {
		return hth;
	}

	public void setHth(String hth) {
		this.hth = hth;
	}

	public Date getHtzbsj() {
		return htzbsj;
	}

	public void setHtzbsj(Date htzbsj) {
		this.htzbsj = htzbsj;
	}

	public Date getJhsj() {
		return jhsj;
	}

	public void setJhsj(Date jhsj) {
		this.jhsj = jhsj;
	}

	public String getGzh() {
		return gzh;
	}

	public void setGzh(String gzh) {
		this.gzh = gzh;
	}

	public Double getCz() {
		return cz;
	}

	public void setCz(Double cz) {
		this.cz = cz;
	}

	public String getGgph() {
		return ggph;
	}

	public void setGgph(String ggph) {
		this.ggph = ggph;
	}

	public Double getGgyl() {
		return ggyl;
	}

	public void setGgyl(Double ggyl) {
		this.ggyl = ggyl;
	}

	public Double getGgdj() {
		return ggdj;
	}

	public void setGgdj(Double ggdj) {
		this.ggdj = ggdj;
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

	public String getByqygg() {
		return byqygg;
	}

	public void setByqygg(String byqygg) {
		this.byqygg = byqygg;
	}

	public Double getByqyyl() {
		return byqyyl;
	}

	public void setByqyyl(Double byqyyl) {
		this.byqyyl = byqyyl;
	}

	public Double getByqydj() {
		return byqydj;
	}

	public void setByqydj(Double byqydj) {
		this.byqydj = byqydj;
	}

	public Double getGcyl() {
		return gcyl;
	}

	public void setGcyl(Double gcyl) {
		this.gcyl = gcyl;
	}

	public Double getGcdj() {
		return gcdj;
	}

	public void setGcdj(Double gcdj) {
		this.gcdj = gcdj;
	}

	public Double getZbyl() {
		return zbyl;
	}

	public void setZbyl(Double zbyl) {
		this.zbyl = zbyl;
	}

	public Double getZbdj() {
		return zbdj;
	}

	public void setZbdj(Double zbdj) {
		this.zbdj = zbdj;
	}

	public Double getQtclcb() {
		return qtclcb;
	}

	public void setQtclcb(Double qtclcb) {
		this.qtclcb = qtclcb;
	}

	public Double getRgjzzfy() {
		return rgjzzfy;
	}

	public void setRgjzzfy(Double rgjzzfy) {
		this.rgjzzfy = rgjzzfy;
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

}
