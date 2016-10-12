package com.tbea.ic.operation.model.entity.cwcpdlml;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cwgb_cpdlml")
public class CpdlmlEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer nf;
	Integer yf;
	Integer cpdl;
	Double ljsr;
	Double ljcb;
	Integer dwid;
	public Integer getNf() {
		return nf;
	}
	public void setNf(Integer nf) {
		this.nf = nf;
	}
	public Integer getYf() {
		return yf;
	}
	public void setYf(Integer yf) {
		this.yf = yf;
	}

	public Integer getCpdl() {
		return cpdl;
	}
	public void setCpdl(Integer cpdl) {
		this.cpdl = cpdl;
	}
	public Double getLjsr() {
		return ljsr;
	}
	public void setLjsr(Double ljsr) {
		this.ljsr = ljsr;
	}
	public Double getLjcb() {
		return ljcb;
	}
	public void setLjcb(Double ljcb) {
		this.ljcb = ljcb;
	}
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
}
