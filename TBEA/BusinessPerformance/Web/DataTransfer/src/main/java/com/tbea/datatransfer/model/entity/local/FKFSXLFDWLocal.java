package com.tbea.datatransfer.model.entity.local;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "yszk_zj_htfkfstj_xl_fdw")
public class FKFSXLFDWLocal extends AbstractReadWriteEntity {

	private Date gxrq;

	private String gsbm;

	private String ny;

	private String khbh;

	private Integer ddzlbs;

	private Double ddzlje;

	private Integer wyfkhtbs;

	private Double wyfkhtje;

	private Integer yfkxybfzshtbs;

	private Double yfkxybfzshtje;

	private Integer yfkzbfzsdsszjhtbs;

	private Double yfkzbfzsdsszjhtje;

	private Integer hwjfhfkblxybfzbshtbs;

	private Double hwjfhfkblxybfzbshtje;

	private Integer zbjbfzshtbs;

	private Double zbjbfzshtje;

	private Integer zbjbfzwhtbs;

	private Double zbjbfzwhtje;

	private Integer wzbjhtbs;

	private Double wzbjhtje;

	private Integer zbqcgynhtbs;

	private Double zbqcgynhtje;

	private Integer wddsjhtbs;

	private Double wddsjhtje;

	private Integer xkxhhtbs;

	private Double xkxhhtje;

	private String sfdrwc;

	private Integer qybh;

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

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public Integer getDdzlbs() {
		return ddzlbs;
	}

	public void setDdzlbs(Integer ddzlbs) {
		this.ddzlbs = ddzlbs;
	}

	public Double getDdzlje() {
		return ddzlje;
	}

	public void setDdzlje(Double ddzlje) {
		this.ddzlje = ddzlje;
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

	public Integer getZbjbfzshtbs() {
		return zbjbfzshtbs;
	}

	public void setZbjbfzshtbs(Integer zbjbfzshtbs) {
		this.zbjbfzshtbs = zbjbfzshtbs;
	}

	public Double getZbjbfzshtje() {
		return zbjbfzshtje;
	}

	public void setZbjbfzshtje(Double zbjbfzshtje) {
		this.zbjbfzshtje = zbjbfzshtje;
	}

	public Integer getZbjbfzwhtbs() {
		return zbjbfzwhtbs;
	}

	public void setZbjbfzwhtbs(Integer zbjbfzwhtbs) {
		this.zbjbfzwhtbs = zbjbfzwhtbs;
	}

	public Double getZbjbfzwhtje() {
		return zbjbfzwhtje;
	}

	public void setZbjbfzwhtje(Double zbjbfzwhtje) {
		this.zbjbfzwhtje = zbjbfzwhtje;
	}

	public Integer getWzbjhtbs() {
		return wzbjhtbs;
	}

	public void setWzbjhtbs(Integer wzbjhtbs) {
		this.wzbjhtbs = wzbjhtbs;
	}

	public Double getWzbjhtje() {
		return wzbjhtje;
	}

	public void setWzbjhtje(Double wzbjhtje) {
		this.wzbjhtje = wzbjhtje;
	}

	public Integer getZbqcgynhtbs() {
		return zbqcgynhtbs;
	}

	public void setZbqcgynhtbs(Integer zbqcgynhtbs) {
		this.zbqcgynhtbs = zbqcgynhtbs;
	}

	public Double getZbqcgynhtje() {
		return zbqcgynhtje;
	}

	public void setZbqcgynhtje(Double zbqcgynhtje) {
		this.zbqcgynhtje = zbqcgynhtje;
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
		return "FKFSXLFDWLocal [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", ny=" + ny + ", khbh=" + khbh + ", ddzlbs=" + ddzlbs
				+ ", ddzlje=" + ddzlje + ", wyfkhtbs=" + wyfkhtbs
				+ ", wyfkhtje=" + wyfkhtje + ", yfkxybfzshtbs=" + yfkxybfzshtbs
				+ ", yfkxybfzshtje=" + yfkxybfzshtje + ", yfkzbfzsdsszjhtbs="
				+ yfkzbfzsdsszjhtbs + ", yfkzbfzsdsszjhtje="
				+ yfkzbfzsdsszjhtje + ", hwjfhfkblxybfzbshtbs="
				+ hwjfhfkblxybfzbshtbs + ", hwjfhfkblxybfzbshtje="
				+ hwjfhfkblxybfzbshtje + ", zbjbfzshtbs=" + zbjbfzshtbs
				+ ", zbjbfzshtje=" + zbjbfzshtje + ", zbjbfzwhtbs="
				+ zbjbfzwhtbs + ", zbjbfzwhtje=" + zbjbfzwhtje + ", wzbjhtbs="
				+ wzbjhtbs + ", wzbjhtje=" + wzbjhtje + ", zbqcgynhtbs="
				+ zbqcgynhtbs + ", zbqcgynhtje=" + zbqcgynhtje + ", wddsjhtbs="
				+ wddsjhtbs + ", wddsjhtje=" + wddsjhtje + ", xkxhhtbs="
				+ xkxhhtbs + ", xkxhhtje=" + xkxhhtje + ", sfdrwc=" + sfdrwc
				+ ", qybh=" + qybh + "]";
	}

}
