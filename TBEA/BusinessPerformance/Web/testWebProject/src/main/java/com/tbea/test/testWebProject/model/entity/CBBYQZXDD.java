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
@Table(name = "cb_zj_byqzx")
public class CBBYQZXDD extends AbstractReadWriteEntity {
	
	
	Date	gxrq	;
	Integer	tbcpbh	;
	Integer	ddzxjd	;
	String	hth	;
	Date	htzbsj	;
	Date	jhsj	;
	String	gzh	;
	Double	cz	;
	String	ggph	;
	Double	ggyl	;
	Double	ggdj	;
	Double	djtyl	;
	Double	djtdj	;
	Double	tjgf	;
	String	byqygg	;
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

	@Column(name="[tbcpbh]")
	public Integer getTbcpbh() {
		return tbcpbh;
	}

	@Column(name="[ddzxjd]")
	public Integer getDdzxjd() {
		return ddzxjd;
	}

	@Column(name="[hth]")
	public String getHth() {
		return hth;
	}

	@Column(name="[htzbsj]")
	public Date getHtzbsj() {
		return htzbsj;
	}

	@Column(name="[jhsj]")
	public Date getJhsj() {
		return jhsj;
	}

	@Column(name="[gzh]")
	public String getGzh() {
		return gzh;
	}

	@Column(name="[cz]")
	public Double getCz() {
		return cz;
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

	@Column(name="[byqygg]")
	public String getByqygg() {
		return byqygg;
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
	 * @param tbcpbh the tbcpbh to set
	 */
	public void setTbcpbh(Integer tbcpbh) {
		this.tbcpbh = tbcpbh;
	}

	/**
	 * @param ddzxjd the ddzxjd to set
	 */
	public void setDdzxjd(Integer ddzxjd) {
		this.ddzxjd = ddzxjd;
	}

	/**
	 * @param hth the hth to set
	 */
	public void setHth(String hth) {
		this.hth = hth;
	}

	/**
	 * @param htzbsj the htzbsj to set
	 */
	public void setHtzbsj(Date htzbsj) {
		this.htzbsj = htzbsj;
	}

	/**
	 * @param jhsj the jhsj to set
	 */
	public void setJhsj(Date jhsj) {
		this.jhsj = jhsj;
	}

	/**
	 * @param gzh the gzh to set
	 */
	public void setGzh(String gzh) {
		this.gzh = gzh;
	}

	/**
	 * @param cz the cz to set
	 */
	public void setCz(Double cz) {
		this.cz = cz;
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
	 * @param byqygg the byqygg to set
	 */
	public void setByqygg(String byqygg) {
		this.byqygg = byqygg;
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
