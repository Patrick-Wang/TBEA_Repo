package com.tbea.test.testWebProject.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cb_zj_byqwg")
public class CBBYQWGDD extends AbstractReadWriteEntity {
	
	Date	gxrq	;
	Integer	zxcpbh	;
	String	wgsj	;
	Double	sjcz	;
	String	ggph	;
	Double	ggyl	;
	Double	ggdj	;
	Double	djtyl	;
	Double	djtdj	;
	Double	tjgf	;
	Double	byqyyl	;
	Double	byqydj	;
	Double	gcyl	;
	Double	gcdj	;
	Double	zbyl	;
	Double	zbdj	;
	Double	qtclcb	;
	Double	rgjzzfy	;
	Double	yf	;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name="[gxrq]")
	public Date getGxrq() {
		return gxrq;
	}

	@Column(name="[zxcpbh]")
	public Integer getZxcpbh() {
		return zxcpbh;
	}

	@Column(name="[wgsj]")
	public String getWgsj() {
		return wgsj;
	}

	@Column(name="[sjcz]")
	public Double getSjcz() {
		return sjcz;
	}

	@Column(name="[ggph]")
	public String getGgph() {
		return ggph;
	}

	@Column(name="[ggyl]")
	public Double getGgyl() {
		return ggyl;
	}

	@Column(name="[ggdj]")
	public Double getGgdj() {
		return ggdj;
	}

	@Column(name="[djtyl]")
	public Double getDjtyl() {
		return djtyl;
	}

	@Column(name="[djtdj]")
	public Double getDjtdj() {
		return djtdj;
	}

	@Column(name="[tjgf]")
	public Double getTjgf() {
		return tjgf;
	}

	@Column(name="[byqyyl]")
	public Double getByqyyl() {
		return byqyyl;
	}

	@Column(name="[byqydj]")
	public Double getByqydj() {
		return byqydj;
	}

	@Column(name="[gcyl]")
	public Double getGcyl() {
		return gcyl;
	}

	@Column(name="[gcdj]")
	public Double getGcdj() {
		return gcdj;
	}

	@Column(name="[zbyl]")
	public Double getZbyl() {
		return zbyl;
	}

	@Column(name="[zbdj]")
	public Double getZbdj() {
		return zbdj;
	}

	@Column(name="[qtclcb]")
	public Double getQtclcb() {
		return qtclcb;
	}

	@Column(name="[rgjzzfy]")
	public Double getRgjzzfy() {
		return rgjzzfy;
	}

	@Column(name="[yf]")
	public Double getYf() {
		return yf;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param zxcpbh the zxcpbh to set
	 */
	public void setZxcpbh(Integer zxcpbh) {
		this.zxcpbh = zxcpbh;
	}

	/**
	 * @param wgsj the wgsj to set
	 */
	public void setWgsj(String wgsj) {
		this.wgsj = wgsj;
	}

	/**
	 * @param sjcz the sjcz to set
	 */
	public void setSjcz(Double sjcz) {
		this.sjcz = sjcz;
	}

	/**
	 * @param ggph the ggph to set
	 */
	public void setGgph(String ggph) {
		this.ggph = ggph;
	}

	/**
	 * @param ggyl the ggyl to set
	 */
	public void setGgyl(Double ggyl) {
		this.ggyl = ggyl;
	}

	/**
	 * @param ggdj the ggdj to set
	 */
	public void setGgdj(Double ggdj) {
		this.ggdj = ggdj;
	}

	/**
	 * @param djtyl the djtyl to set
	 */
	public void setDjtyl(Double djtyl) {
		this.djtyl = djtyl;
	}

	/**
	 * @param djtdj the djtdj to set
	 */
	public void setDjtdj(Double djtdj) {
		this.djtdj = djtdj;
	}

	/**
	 * @param tjgf the tjgf to set
	 */
	public void setTjgf(Double tjgf) {
		this.tjgf = tjgf;
	}

	/**
	 * @param byqyyl the byqyyl to set
	 */
	public void setByqyyl(Double byqyyl) {
		this.byqyyl = byqyyl;
	}

	/**
	 * @param byqydj the byqydj to set
	 */
	public void setByqydj(Double byqydj) {
		this.byqydj = byqydj;
	}

	/**
	 * @param gcyl the gcyl to set
	 */
	public void setGcyl(Double gcyl) {
		this.gcyl = gcyl;
	}

	/**
	 * @param gcdj the gcdj to set
	 */
	public void setGcdj(Double gcdj) {
		this.gcdj = gcdj;
	}

	/**
	 * @param zbyl the zbyl to set
	 */
	public void setZbyl(Double zbyl) {
		this.zbyl = zbyl;
	}

	/**
	 * @param zbdj the zbdj to set
	 */
	public void setZbdj(Double zbdj) {
		this.zbdj = zbdj;
	}

	/**
	 * @param qtclcb the qtclcb to set
	 */
	public void setQtclcb(Double qtclcb) {
		this.qtclcb = qtclcb;
	}

	/**
	 * @param rgjzzfy the rgjzzfy to set
	 */
	public void setRgjzzfy(Double rgjzzfy) {
		this.rgjzzfy = rgjzzfy;
	}

	/**
	 * @param yf the yf to set
	 */
	public void setYf(Double yf) {
		this.yf = yf;
	}

}
