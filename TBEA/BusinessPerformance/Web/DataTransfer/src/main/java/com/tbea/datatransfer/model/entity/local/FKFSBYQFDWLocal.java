package com.tbea.datatransfer.model.entity.local;

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
public class FKFSBYQFDWLocal extends AbstractReadWriteEntity {

	private Date gxrq;

	private String gsbm;

	private String ny;

	private Integer fdwhtddzlbs;

	private Double fdwhtddzlje;

	private Integer wyfkhtbs;

	private Double wyfkhtje;

	private Integer yfkxybfzshtbs;

	private Double yfkxybfzshtje;

	private Integer yfkzbfzsdsszjhtbs;

	private Double yfkzbfzsdsszjhtje;

	private Integer hwjfhfkblxybfzbshtbs;

	private Double hwjfhfkblxybfzbshtje;

	private Integer wddsjhtbs;

	private Double wddsjhtje;

	private Integer zbqdysegyhtbs;;

	private Double zbqdysegyhtje;

	private Integer xkxhhtbs;

	private Double xkxhhtje;

	private String sfdrwc;

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

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public Integer getFdwhtddzlbs() {
		return fdwhtddzlbs;
	}

	public void setFdwhtddzlbs(Integer fdwhtddzlbs) {
		this.fdwhtddzlbs = fdwhtddzlbs;
	}

	public Double getFdwhtddzlje() {
		return fdwhtddzlje;
	}

	public void setFdwhtddzlje(Double fdwhtddzlje) {
		this.fdwhtddzlje = fdwhtddzlje;
	}

	public Integer getWyfkhtbs() {
		return wyfkhtbs;
	}

	public void setWyfkhtbs(Integer wyfkhtbs) {
		this.wyfkhtbs = wyfkhtbs;
	}

	public Double getWyfkhtje() {
		return wyfkhtje;
	}

	public void setWyfkhtje(Double wyfkhtje) {
		this.wyfkhtje = wyfkhtje;
	}

	public Integer getYfkxybfzshtbs() {
		return yfkxybfzshtbs;
	}

	public void setYfkxybfzshtbs(Integer yfkxybfzshtbs) {
		this.yfkxybfzshtbs = yfkxybfzshtbs;
	}

	public Double getYfkxybfzshtje() {
		return yfkxybfzshtje;
	}

	public void setYfkxybfzshtje(Double yfkxybfzshtje) {
		this.yfkxybfzshtje = yfkxybfzshtje;
	}

	public Integer getYfkzbfzsdsszjhtbs() {
		return yfkzbfzsdsszjhtbs;
	}

	public void setYfkzbfzsdsszjhtbs(Integer yfkzbfzsdsszjhtbs) {
		this.yfkzbfzsdsszjhtbs = yfkzbfzsdsszjhtbs;
	}

	public Double getYfkzbfzsdsszjhtje() {
		return yfkzbfzsdsszjhtje;
	}

	public void setYfkzbfzsdsszjhtje(Double yfkzbfzsdsszjhtje) {
		this.yfkzbfzsdsszjhtje = yfkzbfzsdsszjhtje;
	}

	public Integer getHwjfhfkblxybfzbshtbs() {
		return hwjfhfkblxybfzbshtbs;
	}

	public void setHwjfhfkblxybfzbshtbs(Integer hwjfhfkblxybfzbshtbs) {
		this.hwjfhfkblxybfzbshtbs = hwjfhfkblxybfzbshtbs;
	}

	public Double getHwjfhfkblxybfzbshtje() {
		return hwjfhfkblxybfzbshtje;
	}

	public void setHwjfhfkblxybfzbshtje(Double hwjfhfkblxybfzbshtje) {
		this.hwjfhfkblxybfzbshtje = hwjfhfkblxybfzbshtje;
	}

	public Integer getWddsjhtbs() {
		return wddsjhtbs;
	}

	public void setWddsjhtbs(Integer wddsjhtbs) {
		this.wddsjhtbs = wddsjhtbs;
	}

	public Double getWddsjhtje() {
		return wddsjhtje;
	}

	public void setWddsjhtje(Double wddsjhtje) {
		this.wddsjhtje = wddsjhtje;
	}

	public Integer getZbqdysegyhtbs() {
		return zbqdysegyhtbs;
	}

	public void setZbqdysegyhtbs(Integer zbqdysegyhtbs) {
		this.zbqdysegyhtbs = zbqdysegyhtbs;
	}

	public Double getZbqdysegyhtje() {
		return zbqdysegyhtje;
	}

	public void setZbqdysegyhtje(Double zbqdysegyhtje) {
		this.zbqdysegyhtje = zbqdysegyhtje;
	}

	public Integer getXkxhhtbs() {
		return xkxhhtbs;
	}

	public void setXkxhhtbs(Integer xkxhhtbs) {
		this.xkxhhtbs = xkxhhtbs;
	}

	public Double getXkxhhtje() {
		return xkxhhtje;
	}

	public void setXkxhhtje(Double xkxhhtje) {
		this.xkxhhtje = xkxhhtje;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	public Integer getQybh() {
		return qybh;
	}

	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

	@Override
	public String toString() {
		return "FKFSBYQFDWLocal [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", ny=" + ny + ", fdwhtddzlbs=" + fdwhtddzlbs
				+ ", fdwhtddzlje=" + fdwhtddzlje + ", wyfkhtbs=" + wyfkhtbs
				+ ", wyfkhtje=" + wyfkhtje + ", yfkxybfzshtbs=" + yfkxybfzshtbs
				+ ", yfkxybfzshtje=" + yfkxybfzshtje + ", yfkzbfzsdsszjhtbs="
				+ yfkzbfzsdsszjhtbs + ", yfkzbfzsdsszjhtje="
				+ yfkzbfzsdsszjhtje + ", hwjfhfkblxybfzbshtbs="
				+ hwjfhfkblxybfzbshtbs + ", hwjfhfkblxybfzbshtje="
				+ hwjfhfkblxybfzbshtje + ", wddsjhtbs=" + wddsjhtbs
				+ ", wddsjhtje=" + wddsjhtje + ", zbqdysegyhtbs="
				+ zbqdysegyhtbs + ", zbqdysegyhtje=" + zbqdysegyhtje
				+ ", xkxhhtbs=" + xkxhhtbs + ", xkxhhtje=" + xkxhhtje
				+ ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
