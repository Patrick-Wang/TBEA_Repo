package com.tbea.ic.operation.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@Table(name = "system_extend_auth")
public class ExtendAuthority extends AbstractReadWriteEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	Account account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	DWXX dwxx;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	@Column(name = "auth_type")
	Integer authType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}
}
