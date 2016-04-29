package com.tbea.ic.operation.model.entity.wgcpqk.yclbfqk;

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

import com.tbea.ic.operation.model.entity.identifier.common.ClmcEntity;


@Entity
@Table(name = "ylfxgb_yclbfqk_dw_ref_cl")
public class DwxxRefClmcEntity extends AbstractReadWriteEntity implements Serializable {
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

	Integer dwid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	ClmcEntity clmc;
	
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	public ClmcEntity getClmc() {
		return clmc;
	}
	public void setClmc(ClmcEntity clmc) {
		this.clmc = clmc;
	}
	
}
