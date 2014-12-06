package com.tbea.test.testWebProject.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "ztyszkfxb")
public class ZTYSZKFX extends AbstractReadWriteEntity{
	Date	gxrq	;
	String	gsbm	;
	Double	byzmyszkye	;
	Double	byblkzye	;
	Double	byyszksjs	;
	Double	ljsr	;
	String	Zmyszsrb	;
	Double	qntqzmyszkye	;
	Double	qntqblye	;
	Double	qntqyszksjs	;
	Double	qntqsr	;
	String	Qntqzmyszsrb	;
	String	Zmyejqntqzzb	;
	String	Bljqntqzzb	;
	String	Sjysjqntqzzb	;
	String	Srjqntqzzb	;
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
	 * @return the gxrq
	 */
	public Date getGxrq() {
		return gxrq;
	}

	/**
	 * @return the gsbm
	 */
	public String getGsbm() {
		return gsbm;
	}

	/**
	 * @return the byzmyszkye
	 */
	public Double getByzmyszkye() {
		return byzmyszkye;
	}

	/**
	 * @return the byblkzye
	 */
	public Double getByblkzye() {
		return byblkzye;
	}

	/**
	 * @return the byyszksjs
	 */
	public Double getByyszksjs() {
		return byyszksjs;
	}

	/**
	 * @return the ljsr
	 */
	public Double getLjsr() {
		return ljsr;
	}

	/**
	 * @return the zmyszsrb
	 */
	public String getZmyszsrb() {
		return Zmyszsrb;
	}

	/**
	 * @return the qntqzmyszkye
	 */
	public Double getQntqzmyszkye() {
		return qntqzmyszkye;
	}

	/**
	 * @return the qntqblye
	 */
	public Double getQntqblye() {
		return qntqblye;
	}

	/**
	 * @return the qntqyszksjs
	 */
	public Double getQntqyszksjs() {
		return qntqyszksjs;
	}

	/**
	 * @return the qntqsr
	 */
	public Double getQntqsr() {
		return qntqsr;
	}

	/**
	 * @return the qntqzmyszsrb
	 */
	public String getQntqzmyszsrb() {
		return Qntqzmyszsrb;
	}

	/**
	 * @return the zmyejqntqzzb
	 */
	public String getZmyejqntqzzb() {
		return Zmyejqntqzzb;
	}

	/**
	 * @return the bljqntqzzb
	 */
	public String getBljqntqzzb() {
		return Bljqntqzzb;
	}

	/**
	 * @return the sjysjqntqzzb
	 */
	public String getSjysjqntqzzb() {
		return Sjysjqntqzzb;
	}

	/**
	 * @return the srjqntqzzb
	 */
	public String getSrjqntqzzb() {
		return Srjqntqzzb;
	}

	/**
	 * @return the qybh
	 */
	public Integer getQybh() {
		return qybh;
	}

	/**
	 * @param gxrq the gxrq to set
	 */
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}

	/**
	 * @param gsbm the gsbm to set
	 */
	public void setGsbm(String gsbm) {
		this.gsbm = gsbm;
	}

	/**
	 * @param byzmyszkye the byzmyszkye to set
	 */
	public void setByzmyszkye(Double byzmyszkye) {
		this.byzmyszkye = byzmyszkye;
	}

	/**
	 * @param byblkzye the byblkzye to set
	 */
	public void setByblkzye(Double byblkzye) {
		this.byblkzye = byblkzye;
	}

	/**
	 * @param byyszksjs the byyszksjs to set
	 */
	public void setByyszksjs(Double byyszksjs) {
		this.byyszksjs = byyszksjs;
	}

	/**
	 * @param ljsr the ljsr to set
	 */
	public void setLjsr(Double ljsr) {
		this.ljsr = ljsr;
	}

	/**
	 * @param zmyszsrb the zmyszsrb to set
	 */
	public void setZmyszsrb(String zmyszsrb) {
		Zmyszsrb = zmyszsrb;
	}

	/**
	 * @param qntqzmyszkye the qntqzmyszkye to set
	 */
	public void setQntqzmyszkye(Double qntqzmyszkye) {
		this.qntqzmyszkye = qntqzmyszkye;
	}

	/**
	 * @param qntqblye the qntqblye to set
	 */
	public void setQntqblye(Double qntqblye) {
		this.qntqblye = qntqblye;
	}

	/**
	 * @param qntqyszksjs the qntqyszksjs to set
	 */
	public void setQntqyszksjs(Double qntqyszksjs) {
		this.qntqyszksjs = qntqyszksjs;
	}

	/**
	 * @param qntqsr the qntqsr to set
	 */
	public void setQntqsr(Double qntqsr) {
		this.qntqsr = qntqsr;
	}

	/**
	 * @param qntqzmyszsrb the qntqzmyszsrb to set
	 */
	public void setQntqzmyszsrb(String qntqzmyszsrb) {
		Qntqzmyszsrb = qntqzmyszsrb;
	}

	/**
	 * @param zmyejqntqzzb the zmyejqntqzzb to set
	 */
	public void setZmyejqntqzzb(String zmyejqntqzzb) {
		Zmyejqntqzzb = zmyejqntqzzb;
	}

	/**
	 * @param bljqntqzzb the bljqntqzzb to set
	 */
	public void setBljqntqzzb(String bljqntqzzb) {
		Bljqntqzzb = bljqntqzzb;
	}

	/**
	 * @param sjysjqntqzzb the sjysjqntqzzb to set
	 */
	public void setSjysjqntqzzb(String sjysjqntqzzb) {
		Sjysjqntqzzb = sjysjqntqzzb;
	}

	/**
	 * @param srjqntqzzb the srjqntqzzb to set
	 */
	public void setSrjqntqzzb(String srjqntqzzb) {
		Srjqntqzzb = srjqntqzzb;
	}

	/**
	 * @param qybh the qybh to set
	 */
	public void setQybh(Integer qybh) {
		this.qybh = qybh;
	}

}
