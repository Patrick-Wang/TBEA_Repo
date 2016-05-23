package com.tbea.ic.operation.model.entity.cpzlqk;

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

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.NameEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Entity
@Table(name = "cpzlqk_acptjjg_jd_byq")
public class ByqJdAcptjjgEntity extends AbstractReadWriteEntity implements Serializable {
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
	String formul;
	
	
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
	public String getFormul() {
		return formul;
	}
	public void setFormul(String formul) {
		this.formul = formul;
	}
	
	

}
