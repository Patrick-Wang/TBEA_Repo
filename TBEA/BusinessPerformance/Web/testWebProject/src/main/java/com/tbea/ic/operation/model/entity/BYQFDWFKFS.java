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
@Table(name = "yszk_zj_htfkfstj_byq_fdw")
public class BYQFDWFKFS extends AbstractReadWriteEntity{
	
	Date gxrq;
	String gsbm;
	String ny;
	Integer fdwhtddzlbs;
	Double fdwhtddzlje;
	Integer wyfkhtbs;
	Double wyfkhtje;
	Integer yfkxybfzshtbs;
	Double yfkxybfzshtje;
	Integer yfkzbfzsdsszjhtbs;
	Double yfkzbfzsdsszjhtje;
	Integer hwjfhfkblxybfzbshtbs;
	Double hwjfhfkblxybfzbshtje;
	Integer wddsjhtbs;
	Double wddsjhtje;
	Integer zbqdysegyhtbs;;
	Double zbqdysegyhtje;
	Integer xkxhhtbs;
	Double xkxhhtje;
	String sfdrwc;
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

	/**
	 * @return the gxrq
	 */
	public Date getGxrq() {
		return gxrq;
	}

	/**
	 * @return the gsbm
	 */
	public String getGsbm() {
		return gsbm;
	}

	/**
	 * @return the fdwhtddzlbs
	 */
	public Integer getFdwhtddzlbs() {
		return fdwhtddzlbs;
	}

	/**
	 * @return the fdwhtddzlje
	 */
	public Double getFdwhtddzlje() {
		return fdwhtddzlje;
	}

	/**
	 * @return the wyfkhtbs
	 */
	public Integer getWyfkhtbs() {
		return wyfkhtbs;
	}

	/**
	 * @return the wyfkhtje
	 */
	public Double getWyfkhtje() {
		return wyfkhtje;
	}

	/**
	 * @return the yfkxybfzshtbs
	 */
	public Integer getYfkxybfzshtbs() {
		return yfkxybfzshtbs;
	}

	/**
	 * @return the yfkxybfzshtje
	 */
	public Double getYfkxybfzshtje() {
		return yfkxybfzshtje;
	}

	/**
	 * @return the yfkzbfzsdsszjhtbs
	 */
	public Integer getYfkzbfzsdsszjhtbs() {
		return yfkzbfzsdsszjhtbs;
	}

	/**
	 * @return the yfkzbfzsdsszjhtje
	 */
	public Double getYfkzbfzsdsszjhtje() {
		return yfkzbfzsdsszjhtje;
	}

	/**
	 * @return the hwjfhfkblxybfzbshtbs
	 */
	public Integer getHwjfhfkblxybfzbshtbs() {
		return hwjfhfkblxybfzbshtbs;
	}

	/**
	 * @return the hwjfhfkblxybfzbshtje
	 */
	public Double getHwjfhfkblxybfzbshtje() {
		return hwjfhfkblxybfzbshtje;
	}

	/**
	 * @return the wddsjhtbs
	 */
	public Integer getWddsjhtbs() {
		return wddsjhtbs;
	}

	/**
	 * @return the wddsjhtje
	 */
	public Double getWddsjhtje() {
		return wddsjhtje;
	}

	/**
	 * @return the zbqdysegyhtbs
	 */
	public Integer getZbqdysegyhtbs() {
		return zbqdysegyhtbs;
	}

	/**
	 * @return the zbqdysegyhtje
	 */
	public Double getZbqdysegyhtje() {
		return zbqdysegyhtje;
	}

	/**
	 * @return the xkxhhtbs
	 */
	public Integer getXkxhhtbs() {
		return xkxhhtbs;
	}

	/**
	 * @return the xkxhhtje
	 */
	public Double getXkxhhtje() {
		return xkxhhtje;
	}

	/**
	 * @return the sfdrwc
	 */
	public String getSfdrwc() {
		return sfdrwc;
	}

	/**
	 * @return the qybh
	 */
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
	 * @param fdwhtddzlbs the fdwhtddzlbs to set
	 */
	public void setFdwhtddzlbs(Integer fdwhtddzlbs) {
		this.fdwhtddzlbs = fdwhtddzlbs;
	}

	/**
	 * @param fdwhtddzlje the fdwhtddzlje to set
	 */
	public void setFdwhtddzlje(Double fdwhtddzlje) {
		this.fdwhtddzlje = fdwhtddzlje;
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
	 * @param yfkxybfzshtbs the yfkxybfzshtbs to set
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
	 * @param zbqdysegyhtbs the zbqdysegyhtbs to set
	 */
	public void setZbqdysegyhtbs(Integer zbqdysegyhtbs) {
		this.zbqdysegyhtbs = zbqdysegyhtbs;
	}

	/**
	 * @param zbqdysegyhtje the zbqdysegyhtje to set
	 */
	public void setZbqdysegyhtje(Double zbqdysegyhtje) {
		this.zbqdysegyhtje = zbqdysegyhtje;
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

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

}
