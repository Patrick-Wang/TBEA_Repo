package com.tbea.ic.operation.model.entity.xnych;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "xnych_xnych")
public class XnychEntity extends AbstractReadWriteEntity implements Serializable {
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
	Integer dwid;
	Double zmjehj;
	Double epcxmch;
	Double yzrzyxmch;
	Double wzrzyxmch;
	Double yzgzyxmch;
	Double zbhqqfy;
	Integer zt;
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
	public Integer getDwid() {
		return dwid;
	}
	public void setDwid(Integer dwid) {
		this.dwid = dwid;
	}
	public Double getZmjehj() {
		return zmjehj;
	}
	public void setZmjehj(Double zmjehj) {
		this.zmjehj = zmjehj;
	}
	public Double getEpcxmch() {
		return epcxmch;
	}
	public void setEpcxmch(Double epcxmch) {
		this.epcxmch = epcxmch;
	}
	public Double getYzrzyxmch() {
		return yzrzyxmch;
	}
	public void setYzrzyxmch(Double yzrzyxmch) {
		this.yzrzyxmch = yzrzyxmch;
	}
	public Double getWzrzyxmch() {
		return wzrzyxmch;
	}
	public void setWzrzyxmch(Double wzrzyxmch) {
		this.wzrzyxmch = wzrzyxmch;
	}
	public Double getYzgzyxmch() {
		return yzgzyxmch;
	}
	public void setYzgzyxmch(Double yzgzyxmch) {
		this.yzgzyxmch = yzgzyxmch;
	}
	public Double getZbhqqfy() {
		return zbhqqfy;
	}
	public void setZbhqqfy(Double zbhqqfy) {
		this.zbhqqfy = zbhqqfy;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
}
