package com.tbea.ic.operation.model.entity.cbfx;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cbfx_nymyywmlfx")
public class NymyywmlfxEntity extends AbstractReadWriteEntity implements Serializable {
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
	String hzkh;
	String myxm;
	Integer sl;
	Double sr;
	Double cb;
	public String getHzkh() {
		return hzkh;
	}
	public void setHzkh(String hzkh) {
		this.hzkh = hzkh;
	}
	public String getMyxm() {
		return myxm;
	}
	public void setMyxm(String myxm) {
		this.myxm = myxm;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public Double getSr() {
		return sr;
	}
	public void setSr(Double sr) {
		this.sr = sr;
	}
	public Double getCb() {
		return cb;
	}
	public void setCb(Double cb) {
		this.cb = cb;
	}

}
