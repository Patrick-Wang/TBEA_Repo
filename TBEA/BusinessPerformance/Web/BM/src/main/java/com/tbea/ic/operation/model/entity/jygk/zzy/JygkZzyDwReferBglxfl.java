package com.tbea.ic.operation.model.entity.jygk.zzy;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the jygk_zzy_dw_refer_bglxfl database table.
 * 
 */
@Entity
@Table(name="jygk_zzy_dw_refer_bglxfl")
@NamedQuery(name="JygkZzyDwReferBglxfl.findAll", query="SELECT j FROM JygkZzyDwReferBglxfl j")
public class JygkZzyDwReferBglxfl extends cn.com.tbea.template.model.entity.AbstractReadWriteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int bglxid;
	private int dwid;
	private JygkZzyFl jygkZzyFl;
	private int sx;

	public JygkZzyDwReferBglxfl() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flid")
	public JygkZzyFl getJygkZzyFl() {
		return this.jygkZzyFl;
	}

	public void setJygkZzyFl(JygkZzyFl jygkZzyFl) {
		this.jygkZzyFl = jygkZzyFl;
	}


	public int getSx() {
		return this.sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

}