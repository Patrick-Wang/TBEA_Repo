package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jygk_zzy_dw_refer_bglx database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_dw_refer_bglx")
@NamedQuery(name="JygkZzyDwReferBglx.findAll", query="SELECT j FROM JygkZzyDwReferBglx j")
public class JygkZzyDwReferBglx extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int bglxid;
	private int dwid;
	private String wvtype;

	public JygkZzyDwReferBglx() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBglxid() {
		return this.bglxid;
	}

	public void setBglxid(int bglxid) {
		this.bglxid = bglxid;
	}


	public int getDwid() {
		return this.dwid;
	}

	public void setDwid(int dwid) {
		this.dwid = dwid;
	}


	public String getWvtype() {
		return this.wvtype;
	}

	public void setWvtype(String wvtype) {
		this.wvtype = wvtype;
	}

}