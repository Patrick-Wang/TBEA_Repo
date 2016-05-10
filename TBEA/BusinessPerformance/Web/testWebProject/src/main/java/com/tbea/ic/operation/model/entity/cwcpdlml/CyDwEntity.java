package com.tbea.ic.operation.model.entity.cwcpdlml;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import com.tbea.ic.operation.model.entity.identifier.cwgb.CyEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "cwgb_cy_ref_dw")
public class CyDwEntity extends AbstractReadWriteEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	CyEntity cy;
	DWXX dw;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cyid")
	public CyEntity getCy() {
		return cy;
	}
	public void setCy(CyEntity cy) {
		this.cy = cy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDw() {
		return dw;
	}
	public void setDw(DWXX dw) {
		this.dw = dw;
	}
	
	
}
