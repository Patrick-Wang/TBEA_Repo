package com.tbea.ic.operation.model.entity.cpzlqk;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.NameEntity;


@Entity
@Table(name = "cpzlqk_acptjjg_xl")
public class XlAcptjjgEntity extends AbstractReadWriteEntity implements Serializable {
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

	NameEntity cpdl;
	NameEntity cpxl;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpdl")
	public NameEntity getCpdl() {
		return cpdl;
	}
	public void setCpdl(NameEntity cpdl) {
		this.cpdl = cpdl;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpxl")
	public NameEntity getCpxl() {
		return cpxl;
	}
	public void setCpxl(NameEntity cpxl) {
		this.cpxl = cpxl;
	}
}
