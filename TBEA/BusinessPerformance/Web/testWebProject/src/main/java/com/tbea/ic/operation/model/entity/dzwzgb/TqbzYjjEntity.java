package com.tbea.ic.operation.model.entity.dzwzgb;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "XBC_TQBZ_YJJ")
public class TqbzYjjEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double YF;//		numeric(6, 0)
	Double ID;//		numeric(16, 0)
	Double YJJLB;//		numeric(12, 0)
	Double CLLB;//		numeric(12, 0)
	Double QYBH;//		numeric(4, 0)
	Double SCJ;//		numeric(10, 2)
	Double CGJ;//		numeric(10, 2)
	Double CGJ2;//		numeric(10, 2)
	Double MBLRDSJ;//		numeric(10, 2)
	Double SXFYJ;//		numeric(10, 2)
	Timestamp RQ;//		datetime
	Double ZTBJ;//		numeric(12, 0)
	Double CGL;//	 	numeric(12, 2)
	Double DYQHYK;//		numeric(12, 2)
	public Double getYF() {
		return YF;
	}
	public void setYF(Double yF) {
		YF = yF;
	}
	
	@Id
	public Double getID() {
		return ID;
	}
	

	public void setID(Double iD) {
		ID = iD;
	}
	public Double getYJJLB() {
		return YJJLB;
	}
	public void setYJJLB(Double yJJLB) {
		YJJLB = yJJLB;
	}
	public Double getCLLB() {
		return CLLB;
	}
	public void setCLLB(Double cLLB) {
		CLLB = cLLB;
	}
	public Double getQYBH() {
		return QYBH;
	}
	public void setQYBH(Double qYBH) {
		QYBH = qYBH;
	}
	public Double getSCJ() {
		return SCJ;
	}
	public void setSCJ(Double sCJ) {
		SCJ = sCJ;
	}
	public Double getCGJ() {
		return CGJ;
	}
	public void setCGJ(Double cGJ) {
		CGJ = cGJ;
	}
	public Double getCGJ2() {
		return CGJ2;
	}
	public void setCGJ2(Double cGJ2) {
		CGJ2 = cGJ2;
	}
	public Double getMBLRDSJ() {
		return MBLRDSJ;
	}
	public void setMBLRDSJ(Double mBLRDSJ) {
		MBLRDSJ = mBLRDSJ;
	}
	public Double getSXFYJ() {
		return SXFYJ;
	}
	public void setSXFYJ(Double sXFYJ) {
		SXFYJ = sXFYJ;
	}
	public Timestamp getRQ() {
		return RQ;
	}
	public void setRQ(Timestamp rQ) {
		RQ = rQ;
	}
	public Double getZTBJ() {
		return ZTBJ;
	}
	public void setZTBJ(Double zTBJ) {
		ZTBJ = zTBJ;
	}
	public Double getCGL() {
		return CGL;
	}
	public void setCGL(Double cGL) {
		CGL = cGL;
	}
	public Double getDYQHYK() {
		return DYQHYK;
	}
	public void setDYQHYK(Double dYQHYK) {
		DYQHYK = dYQHYK;
	}
	
	

}
