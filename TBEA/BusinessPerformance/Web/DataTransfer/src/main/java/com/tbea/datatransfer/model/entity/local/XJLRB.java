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
@Table(name = "xjlrb")
public class XJLRB extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date rq;

	private Integer sscy;

	private Integer px;

	private String jgmc;

	private Double drlr;

	private Double drlc;

	private Double drjll;

	private Double dylr;

	private Double dylc;

	private Double dyjll;

	private Double dnlr;

	private Double dnlc;

	private Double dnjll;

	private Double bytzs;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Integer getSscy() {
		return sscy;
	}

	public void setSscy(Integer sscy) {
		this.sscy = sscy;
	}

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public Double getDrlr() {
		return drlr;
	}

	public void setDrlr(Double drlr) {
		this.drlr = drlr;
	}

	public Double getDrlc() {
		return drlc;
	}

	public void setDrlc(Double drlc) {
		this.drlc = drlc;
	}

	public Double getDrjll() {
		return drjll;
	}

	public void setDrjll(Double drjll) {
		this.drjll = drjll;
	}

	public Double getDylr() {
		return dylr;
	}

	public void setDylr(Double dylr) {
		this.dylr = dylr;
	}

	public Double getDylc() {
		return dylc;
	}

	public void setDylc(Double dylc) {
		this.dylc = dylc;
	}

	public Double getDyjll() {
		return dyjll;
	}

	public void setDyjll(Double dyjll) {
		this.dyjll = dyjll;
	}

	public Double getDnlr() {
		return dnlr;
	}

	public void setDnlr(Double dnlr) {
		this.dnlr = dnlr;
	}

	public Double getDnlc() {
		return dnlc;
	}

	public void setDnlc(Double dnlc) {
		this.dnlc = dnlc;
	}

	public Double getDnjll() {
		return dnjll;
	}

	public void setDnjll(Double dnjll) {
		this.dnjll = dnjll;
	}

	public Double getBytzs() {
		return bytzs;
	}

	public void setBytzs(Double bytzs) {
		this.bytzs = bytzs;
	}

	@Override
	public String toString() {
		return "XJLRB [id=" + getId() + ", rq=" + rq + ", sscy=" + sscy
				+ ", px=" + px + ", jgmc=" + jgmc + ", drlr=" + drlr
				+ ", drlc=" + drlc + ", drjll=" + drjll + ", dylr=" + dylr
				+ ", dylc=" + dylc + ", dyjll=" + dyjll + ", dnlr=" + dnlr
				+ ", dnlc=" + dnlc + ", dnjll=" + dnjll + ", bytzs=" + bytzs
				+ "]";
	}

}
