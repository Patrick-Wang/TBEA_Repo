package com.tbea.ic.operation.model.entity.jygk;

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

@Entity
@Table(name = "jygk_qxgl")
public class QXGL extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Account account;

	private DWXX dwxx;

	private boolean jhzlr;

	private boolean sjzlr;

	private boolean jhzsh;

	private boolean sjzsh;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_ID")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	public boolean isJhzlr() {
		return jhzlr;
	}

	public void setJhzlr(boolean jhzlr) {
		this.jhzlr = jhzlr;
	}

	public boolean isSjzlr() {
		return sjzlr;
	}

	public void setSjzlr(boolean sjzlr) {
		this.sjzlr = sjzlr;
	}

	public boolean isJhzsh() {
		return jhzsh;
	}

	public void setJhzsh(boolean jhzsh) {
		this.jhzsh = jhzsh;
	}

	public boolean isSjzsh() {
		return sjzsh;
	}

	public void setSjzsh(boolean sjzsh) {
		this.sjzsh = sjzsh;
	}

}
