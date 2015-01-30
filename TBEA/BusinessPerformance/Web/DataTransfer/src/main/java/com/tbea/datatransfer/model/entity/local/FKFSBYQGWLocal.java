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
@Table(name = "yszk_zj_htfkfstj_byq_gwfk")
public class FKFSBYQGWLocal extends AbstractReadWriteEntity {

	private Date gxrq;

	private String gsbm;

	private String ny;

	private Integer gwhtddzlbs;

	private Double gwhtddzlje;

	private Integer n3_4_2_1bs;

	private Double n3_4_2_1je;

	private Integer n3_4_2d5_0d5bs;

	private Double n3_4_2d5_0d5je;

	private Integer n0_9_0_1bs;

	private Double n0_9_0_1je;

	private Integer n1_4_4_1bs;

	private Double n1_4_4_1je;

	private Integer n1_4_4d5_0d5bs;

	private Double n1_4_4d5_0d5je;

	private Integer n0_10_0_0bs;

	private Double n0_10_0_0je;

	private Integer n9d5_0d5bs;

	private Double n9d5_0d5je;

	private Integer qtbs;

	private Double qtje;

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

	@Column(name = "[3_4_2d5_0d5bs]")
	public Integer getN3_4_2d5_0d5bs() {
		return n3_4_2d5_0d5bs;
	}

	public void setN3_4_2d5_0d5bs(Integer n3_4_2d5_0d5bs) {
		this.n3_4_2d5_0d5bs = n3_4_2d5_0d5bs;
	}

	@Column(name = "[3_4_2d5_0d5je]")
	public Double getN3_4_2d5_0d5je() {
		return n3_4_2d5_0d5je;
	}

	public void setN3_4_2d5_0d5je(Double n3_4_2d5_0d5je) {
		this.n3_4_2d5_0d5je = n3_4_2d5_0d5je;
	}

	@Column(name = "[0_9_0_1bs]")
	public Integer getN0_9_0_1bs() {
		return n0_9_0_1bs;
	}

	public void setN0_9_0_1bs(Integer n0_9_0_1bs) {
		this.n0_9_0_1bs = n0_9_0_1bs;
	}

	@Column(name = "[0_9_0_1je]")
	public Double getN0_9_0_1je() {
		return n0_9_0_1je;
	}

	public void setN0_9_0_1je(Double n0_9_0_1je) {
		this.n0_9_0_1je = n0_9_0_1je;
	}

	@Column(name = "[1_4_4_1bs]")
	public Integer getN1_4_4_1bs() {
		return n1_4_4_1bs;
	}

	public void setN1_4_4_1bs(Integer n1_4_4_1bs) {
		this.n1_4_4_1bs = n1_4_4_1bs;
	}

	@Column(name = "[1_4_4_1je]")
	public Double getN1_4_4_1je() {
		return n1_4_4_1je;
	}

	public void setN1_4_4_1je(Double n1_4_4_1je) {
		this.n1_4_4_1je = n1_4_4_1je;
	}

	@Column(name = "[1_4_4d5_0d5bs]")
	public Integer getN1_4_4d5_0d5bs() {
		return n1_4_4d5_0d5bs;
	}

	public void setN1_4_4d5_0d5bs(Integer n1_4_4d5_0d5bs) {
		this.n1_4_4d5_0d5bs = n1_4_4d5_0d5bs;
	}

	@Column(name = "[1_4_4d5_0d5je]")
	public Double getN1_4_4d5_0d5je() {
		return n1_4_4d5_0d5je;
	}

	public void setN1_4_4d5_0d5je(Double n1_4_4d5_0d5je) {
		this.n1_4_4d5_0d5je = n1_4_4d5_0d5je;
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

	@Column(name = "[9d5_0d5bs]")
	public Integer getN9d5_0d5bs() {
		return n9d5_0d5bs;
	}

	public void setN9d5_0d5bs(Integer n9d5_0d5bs) {
		this.n9d5_0d5bs = n9d5_0d5bs;
	}

	@Column(name = "[9d5_0d5je]")
	public Double getN9d5_0d5je() {
		return n9d5_0d5je;
	}

	public void setN9d5_0d5je(Double n9d5_0d5je) {
		this.n9d5_0d5je = n9d5_0d5je;
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
		return "FKFSBYQGWLocal [id=" + getId() + ", gxrq=" + gxrq + ", gsbm=" + gsbm
				+ ", ny=" + ny + ", gwhtddzlbs=" + gwhtddzlbs + ", gwhtddzlje="
				+ gwhtddzlje + ", n3_4_2_1bs=" + n3_4_2_1bs + ", n3_4_2_1je="
				+ n3_4_2_1je + ", n3_4_2d5_0d5bs=" + n3_4_2d5_0d5bs
				+ ", n3_4_2d5_0d5je=" + n3_4_2d5_0d5je + ", n0_9_0_1bs="
				+ n0_9_0_1bs + ", n0_9_0_1je=" + n0_9_0_1je + ", n1_4_4_1bs="
				+ n1_4_4_1bs + ", n1_4_4_1je=" + n1_4_4_1je
				+ ", n1_4_4d5_0d5bs=" + n1_4_4d5_0d5bs + ", n1_4_4d5_0d5je="
				+ n1_4_4d5_0d5je + ", n0_10_0_0bs=" + n0_10_0_0bs
				+ ", n0_10_0_0je=" + n0_10_0_0je + ", n9d5_0d5bs=" + n9d5_0d5bs
				+ ", n9d5_0d5je=" + n9d5_0d5je + ", qtbs=" + qtbs + ", qtje="
				+ qtje + ", sfdrwc=" + sfdrwc + ", qybh=" + qybh + "]";
	}

}
