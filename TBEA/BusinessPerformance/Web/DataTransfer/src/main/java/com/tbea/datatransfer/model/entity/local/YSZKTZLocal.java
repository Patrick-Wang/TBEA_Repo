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
@Table(name = "yszk_zj_yszktz")
public class YSZKTZLocal extends AbstractReadWriteEntity implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private Date gxrq;

	private String htbh;

	private String khbh;

	private String khmc;

	private String khsshy;

	private String kxlb;

	private String kxzt;

	private Double ysje;

	private Date dqrq;

	private Double yhxje;

	private Double yfhje;

	private Date fhrq;

	private Double ykpje;

	private Date kprq;

	private Integer yqyyfl;

	private String sftgflsdqs;

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

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKhsshy() {
		return khsshy;
	}

	public void setKhsshy(String khsshy) {
		this.khsshy = khsshy;
	}

	public String getKxlb() {
		return kxlb;
	}

	public void setKxlb(String kxlb) {
		this.kxlb = kxlb;
	}

	public String getKxzt() {
		return kxzt;
	}

	public void setKxzt(String kxzt) {
		this.kxzt = kxzt;
	}

	public Double getYsje() {
		return ysje;
	}

	public void setYsje(Double ysje) {
		this.ysje = ysje;
	}

	public Date getDqrq() {
		return dqrq;
	}

	public void setDqrq(Date dqrq) {
		this.dqrq = dqrq;
	}

	public Double getYhxje() {
		return yhxje;
	}

	public void setYhxje(Double yhxje) {
		this.yhxje = yhxje;
	}

	public Double getYfhje() {
		return yfhje;
	}

	public void setYfhje(Double yfhje) {
		this.yfhje = yfhje;
	}

	public Date getFhrq() {
		return fhrq;
	}

	public void setFhrq(Date fhrq) {
		this.fhrq = fhrq;
	}

	public Double getYkpje() {
		return ykpje;
	}

	public void setYkpje(Double ykpje) {
		this.ykpje = ykpje;
	}

	public Date getKprq() {
		return kprq;
	}

	public void setKprq(Date kprq) {
		this.kprq = kprq;
	}

	public Integer getYqyyfl() {
		return yqyyfl;
	}

	public void setYqyyfl(Integer yqyyfl) {
		this.yqyyfl = yqyyfl;
	}

	public String getSftgflsdqs() {
		return sftgflsdqs;
	}

	public void setSftgflsdqs(String sftgflsdqs) {
		this.sftgflsdqs = sftgflsdqs;
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
		return "YSZKTZLocal [id=" + getId() + ", gxrq=" + gxrq + ", htbh="
				+ htbh + ", khbh=" + khbh + ", khmc=" + khmc + ", khsshy="
				+ khsshy + ", kxlb=" + kxlb + ", kxzt=" + kxzt + ", ysje="
				+ ysje + ", dqrq=" + dqrq + ", yhxje=" + yhxje + ", yfhje="
				+ yfhje + ", fhrq=" + fhrq + ", ykpje=" + ykpje + ", kprq="
				+ kprq + ", yqyyfl=" + yqyyfl + ", sftgflsdqs=" + sftgflsdqs
				+ ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
