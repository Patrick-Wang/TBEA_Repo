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
@Table(name = "yszk_zj_htfkfstj_xl_nwfk")
public class FKFSXLNWLocal extends AbstractReadWriteEntity {

	private Date gxrq;

	private String ny;

	private String gsbm;

	private String sfjzzb;

	private Integer nwhtddzlbs;

	private Double nwhtddzlje;

	private Integer n1_6_2_1bs;

	private Double n1_6_2_1je;

	private Integer n1_2_6_1bs;

	private Double n1_2_6_1je;

	private Integer n0_09_01bs;

	private Double n0_09_01je;

	private Integer qtbs;

	private Double qtje;

	private Integer zbqcgynhtbs;

	private Double zbqcgynhtje;

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

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}

	public String getGsbm() {
		return gsbm;
	}

	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	public String getSfjzzb() {
		return sfjzzb;
	}

	public void setSfjzzb(String sfjzzb) {
		this.sfjzzb = sfjzzb;
	}

	public Integer getNwhtddzlbs() {
		return nwhtddzlbs;
	}

	public void setNwhtddzlbs(Integer nwhtddzlbs) {
		this.nwhtddzlbs = nwhtddzlbs;
	}

	public Double getNwhtddzlje() {
		return nwhtddzlje;
	}

	public void setNwhtddzlje(Double nwhtddzlje) {
		this.nwhtddzlje = nwhtddzlje;
	}

	@Column(name = "1_6_2_1bs")
	public Integer getN1_6_2_1bs() {
		return n1_6_2_1bs;
	}

	public void setN1_6_2_1bs(Integer n1_6_2_1bs) {
		this.n1_6_2_1bs = n1_6_2_1bs;
	}

	@Column(name = "1_6_2_1je")
	public Double getN1_6_2_1je() {
		return n1_6_2_1je;
	}

	public void setN1_6_2_1je(Double n1_6_2_1je) {
		this.n1_6_2_1je = n1_6_2_1je;
	}

	@Column(name = "1_2_6_1bs")
	public Integer getN1_2_6_1bs() {
		return n1_2_6_1bs;
	}

	public void setN1_2_6_1bs(Integer n1_2_6_1bs) {
		this.n1_2_6_1bs = n1_2_6_1bs;
	}

	@Column(name = "1_2_6_1je")
	public Double getN1_2_6_1je() {
		return n1_2_6_1je;
	}

	public void setN1_2_6_1je(Double n1_2_6_1je) {
		this.n1_2_6_1je = n1_2_6_1je;
	}

	@Column(name = "0_09_01bs")
	public Integer getN0_09_01bs() {
		return n0_09_01bs;
	}

	public void setN0_09_01bs(Integer n0_09_01bs) {
		this.n0_09_01bs = n0_09_01bs;
	}

	@Column(name = "0_09_01je")
	public Double getN0_09_01je() {
		return n0_09_01je;
	}

	public void setN0_09_01je(Double n0_09_01je) {
		this.n0_09_01je = n0_09_01je;
	}

	public Integer getQtbs() {
		return qtbs;
	}

	public void setQtbs(Integer qtbs) {
		this.qtbs = qtbs;
	}

	public Double getQtje() {
		return qtje;
	}

	public void setQtje(Double qtje) {
		this.qtje = qtje;
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
		return "FKFSXLNWLocal [id=" + getId() + ", gxrq=" + gxrq + ", ny=" + ny
				+ ", gsbm=" + gsbm + ", sfjzzb=" + sfjzzb + ", nwhtddzlbs="
				+ nwhtddzlbs + ", nwhtddzlje=" + nwhtddzlje + ", n1_6_2_1bs="
				+ n1_6_2_1bs + ", n1_6_2_1je=" + n1_6_2_1je + ", n1_2_6_1bs="
				+ n1_2_6_1bs + ", n1_2_6_1je=" + n1_2_6_1je + ", n0_09_01bs="
				+ n0_09_01bs + ", n0_09_01je=" + n0_09_01je + ", qtbs=" + qtbs
				+ ", qtje=" + qtje + ", zbqcgynhtbs=" + zbqcgynhtbs
				+ ", zbqcgynhtje=" + zbqcgynhtje + ", sfdrwc=" + sfdrwc
				+ ", qybh=" + qybh + "]";
	}

}
