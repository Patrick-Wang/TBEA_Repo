package com.tbea.datatransfer.model.entity.inner;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "rhkxx")
public class RHKXX extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date hkrq;

	private Double hkje;

	private Double qzqbbc;

	private Double qzzqbc;

	private Double yhkzkjysdhkje;

	private Double jzydyszkzmye;

	private Double jtxdydzjhlzb;

	private Double gdwzxzddhkjh;

	private Double ylj;

	private String zjhlzbwc;

	private String hkjhwcl;

	private Double mqzydhkjhhj;

	private Double qyqb;

	private String yjqyjhwcl;

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

	public Date getHkrq() {
		return hkrq;
	}

	public void setHkrq(Date hkrq) {
		this.hkrq = hkrq;
	}

	public Double getHkje() {
		return hkje;
	}

	public void setHkje(Double hkje) {
		this.hkje = hkje;
	}

	public Double getQzqbbc() {
		return qzqbbc;
	}

	public void setQzqbbc(Double qzqbbc) {
		this.qzqbbc = qzqbbc;
	}

	public Double getQzzqbc() {
		return qzzqbc;
	}

	public void setQzzqbc(Double qzzqbc) {
		this.qzzqbc = qzzqbc;
	}

	public Double getYhkzkjysdhkje() {
		return yhkzkjysdhkje;
	}

	public void setYhkzkjysdhkje(Double yhkzkjysdhkje) {
		this.yhkzkjysdhkje = yhkzkjysdhkje;
	}

	public Double getJzydyszkzmye() {
		return jzydyszkzmye;
	}

	public void setJzydyszkzmye(Double jzydyszkzmye) {
		this.jzydyszkzmye = jzydyszkzmye;
	}

	public Double getJtxdydzjhlzb() {
		return jtxdydzjhlzb;
	}

	public void setJtxdydzjhlzb(Double jtxdydzjhlzb) {
		this.jtxdydzjhlzb = jtxdydzjhlzb;
	}

	public Double getGdwzxzddhkjh() {
		return gdwzxzddhkjh;
	}

	public void setGdwzxzddhkjh(Double gdwzxzddhkjh) {
		this.gdwzxzddhkjh = gdwzxzddhkjh;
	}

	public Double getYlj() {
		return ylj;
	}

	public void setYlj(Double ylj) {
		this.ylj = ylj;
	}

	public String getZjhlzbwc() {
		return zjhlzbwc;
	}

	public void setZjhlzbwc(String zjhlzbwc) {
		this.zjhlzbwc = zjhlzbwc;
	}

	public String getHkjhwcl() {
		return hkjhwcl;
	}

	public void setHkjhwcl(String hkjhwcl) {
		this.hkjhwcl = hkjhwcl;
	}

	public Double getMqzydhkjhhj() {
		return mqzydhkjhhj;
	}

	public void setMqzydhkjhhj(Double mqzydhkjhhj) {
		this.mqzydhkjhhj = mqzydhkjhhj;
	}

	public Double getQyqb() {
		return qyqb;
	}

	public void setQyqb(Double qyqb) {
		this.qyqb = qyqb;
	}

	public String getYjqyjhwcl() {
		return yjqyjhwcl;
	}

	public void setYjqyjhwcl(String yjqyjhwcl) {
		this.yjqyjhwcl = yjqyjhwcl;
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
		return "RHKXX [id=" + getId() + ", hkrq=" + hkrq + ", hkje=" + hkje
				+ ", qzqbbc=" + qzqbbc + ", qzzqbc=" + qzzqbc
				+ ", yhkzkjysdhkje=" + yhkzkjysdhkje + ", jzydyszkzmye="
				+ jzydyszkzmye + ", jtxdydzjhlzb=" + jtxdydzjhlzb
				+ ", gdwzxzddhkjh=" + gdwzxzddhkjh + ", ylj=" + ylj
				+ ", zjhlzbwc=" + zjhlzbwc + ", hkjhwcl=" + hkjhwcl
				+ ", mqzydhkjhhj=" + mqzydhkjhhj + ", qyqb=" + qyqb
				+ ", yjqyjhwcl=" + yjqyjhwcl + ", sfdrwc=" + sfdrwc + ", qybh="
				+ qybh + "]";
	}

}
