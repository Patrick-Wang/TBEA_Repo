package com.tbea.datatransfer.model.entity.zjxl;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_htfkfstj_xl_gwfk")
public class FKFSXLGWXL extends AbstractReadOnlyEntity {

	private Date gxrq;

	private String gsbm;

	private String sfjzzb;

	private Integer gwhtddzlbs;

	private Double gwhtddzlje;

	private Integer n3_06_0_01bs;

	private Double n3_06_0_01je;

	private Integer n0_09_0_01bs;

	private Double n0_09_0_01je;

	private Integer n3_4_2_1bs;

	private Double n3_4_2_1je;

	private Integer n2_5_2_1bs;

	private Double n2_5_2_1je;

	private Integer n2_5_2d5_0d5bs;

	private Double n2_5_2d5_0d5je;

	private Integer n0_10_0_0bs;

	private Double n0_10_0_0je;

	private Integer n0_9d5_0d5bs;

	private Double n0_9d5_0d5je;

	private Integer qtbs;

	private Double qtje;

	private Integer wzbjhtbs;

	private Double wzbjhtje;

	private Integer zbqcgynhtbs;

	private Double zbqcgynhtje;

	private String sfdrwc;

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

	public String getSfjzzb() {
		return sfjzzb;
	}

	public void setSfjzzb(String sfjzzb) {
		this.sfjzzb = sfjzzb;
	}

	public Integer getGwhtddzlbs() {
		return gwhtddzlbs;
	}

	public void setGwhtddzlbs(Integer gwhtddzlbs) {
		this.gwhtddzlbs = gwhtddzlbs;
	}

	public Double getGwhtddzlje() {
		return gwhtddzlje;
	}

	public void setGwhtddzlje(Double gwhtddzlje) {
		this.gwhtddzlje = gwhtddzlje;
	}

	@Column(name = "[3_06_0_01bs]")
	public Integer getN3_06_0_01bs() {
		return n3_06_0_01bs;
	}

	public void setN3_06_0_01bs(Integer n3_06_0_01bs) {
		this.n3_06_0_01bs = n3_06_0_01bs;
	}

	@Column(name = "[3_06_0_01je]")
	public Double getN3_06_0_01je() {
		return n3_06_0_01je;
	}

	public void setN3_06_0_01je(Double n3_06_0_01je) {
		this.n3_06_0_01je = n3_06_0_01je;
	}

	@Column(name = "[0_09_0_01bs]")
	public Integer getN0_09_0_01bs() {
		return n0_09_0_01bs;
	}

	public void setN0_09_0_01bs(Integer n0_09_0_01bs) {
		this.n0_09_0_01bs = n0_09_0_01bs;
	}

	@Column(name = "[0_09_0_01je]")
	public Double getN0_09_0_01je() {
		return n0_09_0_01je;
	}

	public void setN0_09_0_01je(Double n0_09_0_01je) {
		this.n0_09_0_01je = n0_09_0_01je;
	}

	@Column(name = "[3_4_2_1bs]")
	public Integer getN3_4_2_1bs() {
		return n3_4_2_1bs;
	}

	public void setN3_4_2_1bs(Integer n3_4_2_1bs) {
		this.n3_4_2_1bs = n3_4_2_1bs;
	}

	@Column(name = "[3_4_2_1je]")
	public Double getN3_4_2_1je() {
		return n3_4_2_1je;
	}

	public void setN3_4_2_1je(Double n3_4_2_1je) {
		this.n3_4_2_1je = n3_4_2_1je;
	}

	@Column(name = "[2_5_2_1bs]")
	public Integer getN2_5_2_1bs() {
		return n2_5_2_1bs;
	}

	public void setN2_5_2_1bs(Integer n2_5_2_1bs) {
		this.n2_5_2_1bs = n2_5_2_1bs;
	}

	@Column(name = "[2_5_2_1je]")
	public Double getN2_5_2_1je() {
		return n2_5_2_1je;
	}

	public void setN2_5_2_1je(Double n2_5_2_1je) {
		this.n2_5_2_1je = n2_5_2_1je;
	}

	@Column(name = "[2_5_2d5_0d5bs]")
	public Integer getN2_5_2d5_0d5bs() {
		return n2_5_2d5_0d5bs;
	}

	public void setN2_5_2d5_0d5bs(Integer n2_5_2d5_0d5bs) {
		this.n2_5_2d5_0d5bs = n2_5_2d5_0d5bs;
	}

	@Column(name = "[2_5_2d5_0d5je]")
	public Double getN2_5_2d5_0d5je() {
		return n2_5_2d5_0d5je;
	}

	public void setN2_5_2d5_0d5je(Double n2_5_2d5_0d5je) {
		this.n2_5_2d5_0d5je = n2_5_2d5_0d5je;
	}

	@Column(name = "[0_10_0_0bs]")
	public Integer getN0_10_0_0bs() {
		return n0_10_0_0bs;
	}

	public void setN0_10_0_0bs(Integer n0_10_0_0bs) {
		this.n0_10_0_0bs = n0_10_0_0bs;
	}

	@Column(name = "[0_10_0_0je]")
	public Double getN0_10_0_0je() {
		return n0_10_0_0je;
	}

	public void setN0_10_0_0je(Double n0_10_0_0je) {
		this.n0_10_0_0je = n0_10_0_0je;
	}

	@Column(name = "[0_9d5_0d5bs]")
	public Integer getN0_9d5_0d5bs() {
		return n0_9d5_0d5bs;
	}

	public void setN0_9d5_0d5bs(Integer n0_9d5_0d5bs) {
		this.n0_9d5_0d5bs = n0_9d5_0d5bs;
	}

	@Column(name = "[0_9d5_0d5je]")
	public Double getN0_9d5_0d5je() {
		return n0_9d5_0d5je;
	}

	public void setN0_9d5_0d5je(Double n0_9d5_0d5je) {
		this.n0_9d5_0d5je = n0_9d5_0d5je;
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

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "FKFSXLGWDL [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", sfjzzb=" + sfjzzb + ", gwhtddzlbs=" + gwhtddzlbs
				+ ", gwhtddzlje=" + gwhtddzlje + ", n3_06_0_01bs="
				+ n3_06_0_01bs + ", n3_06_0_01je=" + n3_06_0_01je
				+ ", n0_09_0_01bs=" + n0_09_0_01bs + ", n0_09_0_01je="
				+ n0_09_0_01je + ", n3_4_2_1bs=" + n3_4_2_1bs + ", n3_4_2_1je="
				+ n3_4_2_1je + ", n2_5_2_1bs=" + n2_5_2_1bs + ", n2_5_2_1je="
				+ n2_5_2_1je + ", n2_5_2d5_0d5bs=" + n2_5_2d5_0d5bs
				+ ", n2_5_2d5_0d5je=" + n2_5_2d5_0d5je + ", n0_10_0_0bs="
				+ n0_10_0_0bs + ", n0_10_0_0je=" + n0_10_0_0je
				+ ", n0_9d5_0d5bs=" + n0_9d5_0d5bs + ", n0_9d5_0d5je="
				+ n0_9d5_0d5je + ", qtbs=" + qtbs + ", qtje=" + qtje
				+ ", wzbjhtbs=" + wzbjhtbs + ", wzbjhtje=" + wzbjhtje
				+ ", zbqcgynhtbs=" + zbqcgynhtbs + ", zbqcgynhtje="
				+ zbqcgynhtje + ", sfdrwc=" + sfdrwc + "]";
	}

}
