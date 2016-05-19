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
@Table(name = "rhkxx")
public class RHKXX  extends AbstractReadWriteEntity{
	
	Date	Hkrq	;
	Double	Hkje	;
	Double	qzqbbc	;
	Double	qzzqbc	;
	Double	yhkzkjysdhkje	;
	Double	jzydyszkzmye	;
	Double	jtxdydzjhlzb	;
	Double	Gdwzxzddhkjh	;
	Double	ylj	;
	String	Zjhlzbwc	;
	String	Hkjhwcl	;
	Double	Mqzydhkjhhj	;
	Double	Qyqb	;
	String	yjqyjhwcl	;
	String	Sfdrwc	;
	Integer	qybh	;

	
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
	 * @return the hkrq
	 */
	public Date getHkrq() {
		return Hkrq;
	}

	/**
	 * @return the hkje
	 */
	public Double getHkje() {
		return Hkje;
	}

	/**
	 * @return the qzqbbc
	 */
	public Double getQzqbbc() {
		return qzqbbc;
	}

	/**
	 * @return the qzzqbc
	 */
	public Double getQzzqbc() {
		return qzzqbc;
	}

	/**
	 * @return the yhkzkjysdhkje
	 */
	public Double getYhkzkjysdhkje() {
		return yhkzkjysdhkje;
	}

	/**
	 * @return the jzydyszkzmye
	 */
	public Double getJzydyszkzmye() {
		return jzydyszkzmye;
	}

	/**
	 * @return the jtxdydzjhlzb
	 */
	public Double getJtxdydzjhlzb() {
		return jtxdydzjhlzb;
	}

	/**
	 * @return the gdwzxzddhkjh
	 */
	public Double getGdwzxzddhkjh() {
		return Gdwzxzddhkjh;
	}

	/**
	 * @return the ylj
	 */
	public Double getYlj() {
		return ylj;
	}

	/**
	 * @return the zjhlzbwc
	 */
	public String getZjhlzbwc() {
		return Zjhlzbwc;
	}

	/**
	 * @return the hkjhwcl
	 */
	public String getHkjhwcl() {
		return Hkjhwcl;
	}

	/**
	 * @return the mqzydhkjhhj
	 */
	public Double getMqzydhkjhhj() {
		return Mqzydhkjhhj;
	}

	/**
	 * @return the qyqb
	 */
	public Double getQyqb() {
		return Qyqb;
	}

	/**
	 * @return the yjqyjhwcl
	 */
	public String getYjqyjhwcl() {
		return yjqyjhwcl;
	}

	/**
	 * @return the sfdrwc
	 */
	public String getSfdrwc() {
		return Sfdrwc;
	}

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param hkrq the hkrq to set
	 */
	public void setHkrq(Date hkrq) {
		Hkrq = hkrq;
	}

	/**
	 * @param hkje the hkje to set
	 */
	public void setHkje(Double hkje) {
		Hkje = hkje;
	}

	/**
	 * @param qzqbbc the qzqbbc to set
	 */
	public void setQzqbbc(Double qzqbbc) {
		this.qzqbbc = qzqbbc;
	}

	/**
	 * @param qzzqbc the qzzqbc to set
	 */
	public void setQzzqbc(Double qzzqbc) {
		this.qzzqbc = qzzqbc;
	}

	/**
	 * @param yhkzkjysdhkje the yhkzkjysdhkje to set
	 */
	public void setYhkzkjysdhkje(Double yhkzkjysdhkje) {
		this.yhkzkjysdhkje = yhkzkjysdhkje;
	}

	/**
	 * @param jzydyszkzmye the jzydyszkzmye to set
	 */
	public void setJzydyszkzmye(Double jzydyszkzmye) {
		this.jzydyszkzmye = jzydyszkzmye;
	}

	/**
	 * @param jtxdydzjhlzb the jtxdydzjhlzb to set
	 */
	public void setJtxdydzjhlzb(Double jtxdydzjhlzb) {
		this.jtxdydzjhlzb = jtxdydzjhlzb;
	}

	/**
	 * @param gdwzxzddhkjh the gdwzxzddhkjh to set
	 */
	public void setGdwzxzddhkjh(Double gdwzxzddhkjh) {
		Gdwzxzddhkjh = gdwzxzddhkjh;
	}

	/**
	 * @param ylj the ylj to set
	 */
	public void setYlj(Double ylj) {
		this.ylj = ylj;
	}

	/**
	 * @param zjhlzbwc the zjhlzbwc to set
	 */
	public void setZjhlzbwc(String zjhlzbwc) {
		Zjhlzbwc = zjhlzbwc;
	}

	/**
	 * @param hkjhwcl the hkjhwcl to set
	 */
	public void setHkjhwcl(String hkjhwcl) {
		Hkjhwcl = hkjhwcl;
	}

	/**
	 * @param mqzydhkjhhj the mqzydhkjhhj to set
	 */
	public void setMqzydhkjhhj(Double mqzydhkjhhj) {
		Mqzydhkjhhj = mqzydhkjhhj;
	}

	/**
	 * @param qyqb the qyqb to set
	 */
	public void setQyqb(Double qyqb) {
		Qyqb = qyqb;
	}

	/**
	 * @param yjqyjhwcl the yjqyjhwcl to set
	 */
	public void setYjqyjhwcl(String yjqyjhwcl) {
		this.yjqyjhwcl = yjqyjhwcl;
	}

	/**
	 * @param sfdrwc the sfdrwc to set
	 */
	public void setSfdrwc(String sfdrwc) {
		Sfdrwc = sfdrwc;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}
}
