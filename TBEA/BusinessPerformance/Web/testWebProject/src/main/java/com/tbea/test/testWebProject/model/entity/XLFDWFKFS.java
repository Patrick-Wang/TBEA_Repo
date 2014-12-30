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
@Table(name = "yszk_zj_htfkfstj_xl_fdw")
public class XLFDWFKFS extends AbstractReadWriteEntity {
	Date gxrq;
	String gsbm;
	String ny;
	String	khbh	;
	Integer	ddzlbs	;
	Double	ddzlje	;
	Integer	wyfkhtbs	;
	Double	wyfkhtje	;
	Integer	yfkxybfzshtbs	;
	Double	yfkxybfzshtje	;
	Integer	yfkzbfzsdsszjhtbs	;
	Double	yfkzbfzsdsszjhtje	;
	Integer	hwjfhfkblxybfzbshtbs	;
	Double	hwjfhfkblxybfzbshtje	;
	Integer	zbjbfzshtbs	;
	Double	zbjbfzshtje	;
	Integer	zbjbfzwhtbs	;
	Double	zbjbfzwhtje	;
	Integer	wzbjhtbs	;
	Double	wzbjhtje	;
	Integer	zbqcgynhtbs	;
	Double	zbqcgynhtje	;
	Integer	wddsjhtbs	;
	Double	wddsjhtje	;
	Integer	xkxhhtbs	;
	Double	xkxhhtje	;
	String sfdrwc;
	Integer qybh;
	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@Column(name="gxrq")
	public Date getGxrq() {
		return gxrq;
	}

	@Column(name="gsbm")
	public String getGsbm() {
		return gsbm;
	}

	@Column(name="khbh")
	public String getKhbh() {
		return khbh;
	}

	@Column(name="ddzlbs")
	public Integer getDdzlbs() {
		return ddzlbs;
	}

	@Column(name="ddzlje")
	public Double getDdzlje() {
		return ddzlje;
	}

	@Column(name="wyfkhtbs")
	public Integer getWyfkhtbs() {
		return wyfkhtbs;
	}

	@Column(name="wyfkhtje")
	public Double getWyfkhtje() {
		return wyfkhtje;
	}

	@Column(name="yfkxybfzshtbs")
	public Integer getYfkxybfzshtbs() {
		return yfkxybfzshtbs;
	}

	@Column(name="yfkxybfzshtje")
	public Double getYfkxybfzshtje() {
		return yfkxybfzshtje;
	}

	@Column(name="yfkzbfzsdsszjhtbs")
	public Integer getYfkzbfzsdsszjhtbs() {
		return yfkzbfzsdsszjhtbs;
	}

	@Column(name="yfkzbfzsdsszjhtje")
	public Double getYfkzbfzsdsszjhtje() {
		return yfkzbfzsdsszjhtje;
	}

	@Column(name="hwjfhfkblxybfzbshtbs")
	public Integer getHwjfhfkblxybfzbshtbs() {
		return hwjfhfkblxybfzbshtbs;
	}

	@Column(name="hwjfhfkblxybfzbshtje")
	public Double getHwjfhfkblxybfzbshtje() {
		return hwjfhfkblxybfzbshtje;
	}

	@Column(name="zbjbfzshtbs")
	public Integer getZbjbfzshtbs() {
		return zbjbfzshtbs;
	}

	@Column(name="zbjbfzshtje")
	public Double getZbjbfzshtje() {
		return zbjbfzshtje;
	}

	@Column(name="zbjbfzwhtbs")
	public Integer getZbjbfzwhtbs() {
		return zbjbfzwhtbs;
	}

	@Column(name="zbjbfzwhtje")
	public Double getZbjbfzwhtje() {
		return zbjbfzwhtje;
	}

	@Column(name="wzbjhtbs")
	public Integer getWzbjhtbs() {
		return wzbjhtbs;
	}

	@Column(name="wzbjhtje")
	public Double getWzbjhtje() {
		return wzbjhtje;
	}

	@Column(name="zbqcgynhtbs")
	public Integer getZbqcgynhtbs() {
		return zbqcgynhtbs;
	}

	@Column(name="zbqcgynhtje")
	public Double getZbqcgynhtje() {
		return zbqcgynhtje;
	}

	@Column(name="wddsjhtbs")
	public Integer getWddsjhtbs() {
		return wddsjhtbs;
	}

	@Column(name="wddsjhtje")
	public Double getWddsjhtje() {
		return wddsjhtje;
	}

	@Column(name="xkxhhtbs")
	public Integer getXkxhhtbs() {
		return xkxhhtbs;
	}

	@Column(name="xkxhhtje")
	public Double getXkxhhtje() {
		return xkxhhtje;
	}

	@Column(name="sfdrwc")
	public String getSfdrwc() {
		return sfdrwc;
	}

	@Column(name="qybh")
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param gsbm the gsbm to set
	 */
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	/**
	 * @param khbh the khbh to set
	 */
	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	/**
	 * @param ddzlbs the ddzlbs to set
	 */
	public void setDdzlbs(Integer ddzlbs) {
		this.ddzlbs = ddzlbs;
	}

	/**
	 * @param ddzlje the ddzlje to set
	 */
	public void setDdzlje(Double ddzlje) {
		this.ddzlje = ddzlje;
	}

	/**
	 * @param wyfkhtbs the wyfkhtbs to set
	 */
	public void setWyfkhtbs(Integer wyfkhtbs) {
		this.wyfkhtbs = wyfkhtbs;
	}

	/**
	 * @param wyfkhtje the wyfkhtje to set
	 */
	public void setWyfkhtje(Double wyfkhtje) {
		this.wyfkhtje = wyfkhtje;
	}

	/**
	 * @param fkxybfzshtbsxybfzshtbs the yfkxybfzshtbs to set
	 */
	public void setYfkxybfzshtbs(Integer yfkxybfzshtbs) {
		this.yfkxybfzshtbs = yfkxybfzshtbs;
	}

	/**
	 * @param yfkxybfzshtje the yfkxybfzshtje to set
	 */
	public void setYfkxybfzshtje(Double yfkxybfzshtje) {
		this.yfkxybfzshtje = yfkxybfzshtje;
	}

	/**
	 * @param yfkzbfzsdsszjhtbs the yfkzbfzsdsszjhtbs to set
	 */
	public void setYfkzbfzsdsszjhtbs(Integer yfkzbfzsdsszjhtbs) {
		this.yfkzbfzsdsszjhtbs = yfkzbfzsdsszjhtbs;
	}

	/**
	 * @param yfkzbfzsdsszjhtje the yfkzbfzsdsszjhtje to set
	 */
	public void setYfkzbfzsdsszjhtje(Double yfkzbfzsdsszjhtje) {
		this.yfkzbfzsdsszjhtje = yfkzbfzsdsszjhtje;
	}

	/**
	 * @param hwjfhfkblxybfzbshtbs the hwjfhfkblxybfzbshtbs to set
	 */
	public void setHwjfhfkblxybfzbshtbs(Integer hwjfhfkblxybfzbshtbs) {
		this.hwjfhfkblxybfzbshtbs = hwjfhfkblxybfzbshtbs;
	}

	/**
	 * @param hwjfhfkblxybfzbshtje the hwjfhfkblxybfzbshtje to set
	 */
	public void setHwjfhfkblxybfzbshtje(Double hwjfhfkblxybfzbshtje) {
		this.hwjfhfkblxybfzbshtje = hwjfhfkblxybfzbshtje;
	}

	/**
	 * @param zbjbfzshtbs the zbjbfzshtbs to set
	 */
	public void setZbjbfzshtbs(Integer zbjbfzshtbs) {
		this.zbjbfzshtbs = zbjbfzshtbs;
	}

	/**
	 * @param zbjbfzshtje the zbjbfzshtje to set
	 */
	public void setZbjbfzshtje(Double zbjbfzshtje) {
		this.zbjbfzshtje = zbjbfzshtje;
	}

	/**
	 * @param zbjbfzwhtbs the zbjbfzwhtbs to set
	 */
	public void setZbjbfzwhtbs(Integer zbjbfzwhtbs) {
		this.zbjbfzwhtbs = zbjbfzwhtbs;
	}

	/**
	 * @param zbjbfzwhtje the zbjbfzwhtje to set
	 */
	public void setZbjbfzwhtje(Double zbjbfzwhtje) {
		this.zbjbfzwhtje = zbjbfzwhtje;
	}

	/**
	 * @param wzbjhtbs the wzbjhtbs to set
	 */
	public void setWzbjhtbs(Integer wzbjhtbs) {
		this.wzbjhtbs = wzbjhtbs;
	}

	/**
	 * @param wzbjhtje the wzbjhtje to set
	 */
	public void setWzbjhtje(Double wzbjhtje) {
		this.wzbjhtje = wzbjhtje;
	}

	/**
	 * @param zbqcgynhtbs the zbqcgynhtbs to set
	 */
	public void setZbqcgynhtbs(Integer zbqcgynhtbs) {
		this.zbqcgynhtbs = zbqcgynhtbs;
	}

	/**
	 * @param zbqcgynhtje the zbqcgynhtje to set
	 */
	public void setZbqcgynhtje(Double zbqcgynhtje) {
		this.zbqcgynhtje = zbqcgynhtje;
	}

	/**
	 * @param wddsjhtbs the wddsjhtbs to set
	 */
	public void setWddsjhtbs(Integer wddsjhtbs) {
		this.wddsjhtbs = wddsjhtbs;
	}

	/**
	 * @param wddsjhtje the wddsjhtje to set
	 */
	public void setWddsjhtje(Double wddsjhtje) {
		this.wddsjhtje = wddsjhtje;
	}

	/**
	 * @param xkxhhtbs the xkxhhtbs to set
	 */
	public void setXkxhhtbs(Integer xkxhhtbs) {
		this.xkxhhtbs = xkxhhtbs;
	}

	/**
	 * @param xkxhhtje the xkxhhtje to set
	 */
	public void setXkxhhtje(Double xkxhhtje) {
		this.xkxhhtje = xkxhhtje;
	}

	/**
	 * @param sfdrwc the sfdrwc to set
	 */
	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}
}
