package com.tbea.ic.operation.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "cb_zj_byqtb")
public class CBBYQTBDD extends AbstractReadWriteEntity {
	

	Date	gxrq	;
	String	xmxx	;
	Date	tbbjsj	;
	String	yjjhsj	;
	String	xh	;
	String	dy	;
	String	cl	;
	Double	cz	;
	String	yjkbsj	;
	Double	yczbgl	;
	String	ggph	;
	Double	ggyl	;
	Double	ggdj	;
	Double	djtyl	;
	Double	djtdj	;
	Double	byqyyl	;
	Double	byqydj	;
	Double	gcyl	;
	Double	gcdj	;
	Double	zbyl	;
	Double	zbdj	;
	Double	qtclcb	;
	Double	rgjzzfy	;
	Double	yf	;
	Integer qybh;
	
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

	@Column(name="[xmxx]")
	public String getXmxx() {
		return xmxx;
	}

	@Column(name="[tbbjsj]")
	public Date getTbbjsj() {
		return tbbjsj;
	}

	@Column(name="[yjjhsj]")
	public String getYjjhsj() {
		return yjjhsj;
	}

	@Column(name="[xh]")
	public String getXh() {
		return xh;
	}

	@Column(name="[dy]")
	public String getDy() {
		return dy;
	}

	@Column(name="[cl]")
	public String getCl() {
		return cl;
	}

	@Column(name="[cz]")
	public Double getCz() {
		return cz;
	}

	@Column(name="[yjkbsj]")
	public String getYjkbsj() {
		return yjkbsj;
	}

	@Column(name="[yczbgl]")
	public Double getYczbgl() {
		return yczbgl;
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
	 * @param xmxx the xmxx to set
	 */
	public void setXmxx(String xmxx) {
		this.xmxx = xmxx;
	}

	/**
	 * @param tbbjsj the tbbjsj to set
	 */
	public void setTbbjsj(Date tbbjsj) {
		this.tbbjsj = tbbjsj;
	}

	/**
	 * @param yjjhsj the yjjhsj to set
	 */
	public void setYjjhsj(String yjjhsj) {
		this.yjjhsj = yjjhsj;
	}

	/**
	 * @param xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(String dy) {
		this.dy = dy;
	}

	/**
	 * @param cl the cl to set
	 */
	public void setCl(String cl) {
		this.cl = cl;
	}

	/**
	 * @param cz the cz to set
	 */
	public void setCz(Double cz) {
		this.cz = cz;
	}

	/**
	 * @param yjkbsj the yjkbsj to set
	 */
	public void setYjkbsj(String yjkbsj) {
		this.yjkbsj = yjkbsj;
	}

	/**
	 * @param yczbgl the yczbgl to set
	 */
	public void setYczbgl(Double yczbgl) {
		this.yczbgl = yczbgl;
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

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}
	
}
