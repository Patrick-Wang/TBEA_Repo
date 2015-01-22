package com.tbea.datatransfer.model.entity.zjsb;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

@Entity
@Table(name = "yszk_zj_htfkfstj_byq_nwfk")
public class FKFSBYQNWSB extends AbstractReadOnlyEntity {

	private Date gxrq;

	private String gsbm;

	private Integer nwhtddzlbs;

	private Double nwhtddzlje;

	private Integer n3_3_3_1bs;

	private Double n3_3_3_1je;

	private Integer n1_4_4_0d5_0d5bs;

	private Double n1_4_4_0d5_0d5je;

	private Integer n1_2_6d5_0d5bs;

	private Double n1_2_6d5_0d5je;

	private Integer n1_4_4d5_0d5bs;

	private Double n1_4_4d5_0d5je;

	private Integer qtybs;

	private Double qtyje;

	private Integer qtebs;

	private Double qteje;

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

	@Column(name = "[3_3_3_1bs]")
	public Integer getN3_3_3_1bs() {
		return n3_3_3_1bs;
	}

	public void setN3_3_3_1bs(Integer n3_3_3_1bs) {
		this.n3_3_3_1bs = n3_3_3_1bs;
	}

	@Column(name = "[3_3_3_1je]")
	public Double getN3_3_3_1je() {
		return n3_3_3_1je;
	}

	public void setN3_3_3_1je(Double n3_3_3_1je) {
		this.n3_3_3_1je = n3_3_3_1je;
	}

	@Column(name = "[1_4_4_0d5_0d5bs]")
	public Integer getN1_4_4_0d5_0d5bs() {
		return n1_4_4_0d5_0d5bs;
	}

	public void setN1_4_4_0d5_0d5bs(Integer n1_4_4_0d5_0d5bs) {
		this.n1_4_4_0d5_0d5bs = n1_4_4_0d5_0d5bs;
	}

	@Column(name = "[1_4_4_0d5_0d5je]")
	public Double getN1_4_4_0d5_0d5je() {
		return n1_4_4_0d5_0d5je;
	}

	public void setN1_4_4_0d5_0d5je(Double n1_4_4_0d5_0d5je) {
		this.n1_4_4_0d5_0d5je = n1_4_4_0d5_0d5je;
	}

	@Column(name = "[1_2_6d5_0d5bs]")
	public Integer getN1_2_6d5_0d5bs() {
		return n1_2_6d5_0d5bs;
	}

	public void setN1_2_6d5_0d5bs(Integer n1_2_6d5_0d5bs) {
		this.n1_2_6d5_0d5bs = n1_2_6d5_0d5bs;
	}

	@Column(name = "[1_2_6d5_0d5je]")
	public Double getN1_2_6d5_0d5je() {
		return n1_2_6d5_0d5je;
	}

	public void setN1_2_6d5_0d5je(Double n1_2_6d5_0d5je) {
		this.n1_2_6d5_0d5je = n1_2_6d5_0d5je;
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

	public Integer getQtybs() {
		return qtybs;
	}

	public void setQtybs(Integer qtybs) {
		this.qtybs = qtybs;
	}

	public Double getQtyje() {
		return qtyje;
	}

	public void setQtyje(Double qtyje) {
		this.qtyje = qtyje;
	}

	public Integer getQtebs() {
		return qtebs;
	}

	public void setQtebs(Integer qtebs) {
		this.qtebs = qtebs;
	}

	public Double getQteje() {
		return qteje;
	}

	public void setQteje(Double qteje) {
		this.qteje = qteje;
	}

	public String getSfdrwc() {
		return sfdrwc;
	}

	public void setSfdrwc(String sfdrwc) {
		this.sfdrwc = sfdrwc;
	}

	@Override
	public String toString() {
		return "FKFSBYQNWTB [id=" + getId() + ", gxrq=" + gxrq + ", gsbm="
				+ gsbm + ", nwhtddzlbs=" + nwhtddzlbs + ", nwhtddzlje="
				+ nwhtddzlje + ", n3_3_3_1bs=" + n3_3_3_1bs + ", n3_3_3_1je="
				+ n3_3_3_1je + ", n1_4_4_0d5_0d5bs=" + n1_4_4_0d5_0d5bs
				+ ", n1_4_4_0d5_0d5je=" + n1_4_4_0d5_0d5je
				+ ", n1_2_6d5_0d5bs=" + n1_2_6d5_0d5bs + ", n1_2_6d5_0d5je="
				+ n1_2_6d5_0d5je + ", n1_4_4d5_0d5bs=" + n1_4_4d5_0d5bs
				+ ", n1_4_4d5_0d5je=" + n1_4_4d5_0d5je + ", qtybs=" + qtybs
				+ ", qtyje=" + qtyje + ", qtebs=" + qtebs + ", qteje=" + qteje
				+ ", sfdrwc=" + sfdrwc + "]";
	}

}
