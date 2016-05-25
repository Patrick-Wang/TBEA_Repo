package com.tbea.ic.operation.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ExtendAuthority extends AbstractReadWriteEntity implements Serializable {
	
	
	public enum AuthType{
		EMPTY,
		YSZKDailyReportEntry,//1
		JYAnalysisEntry,//2
		JYAnalysisLookup,//3
		YSZKDialyLookup,//4
		XJLDialyLookup,//5
		JYEntryLookup,//6
		PriceLib,//7
		YszkgbEntry,//8
		YszkgbLookup,//9
		ChgbEntry,//10
		ChgbLookup,//11
		SbdgbEntry,//12
		SbdgbLookup,//13
		NygbEntry,//14
		NygbLookup,//15
		XnygbEntry,//16
		XnygbLookup,//17
		NYzbscqkEntry, //18
		NYzbscqkLookup, //19
		QualityLookup, //20
		QualityEntry,  //21
		QualityApprove, //22
		FinanceLookup,	//23
		FinanceEntry	//24
	}
	
	private static final long serialVersionUID = 1L;
	
	Integer authType;
	

	Account account;
	
	
	DWXX dwxx;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return super.getId();
	}

	public void setId(int id) {
		super.setId(id);
	}
	
	@ManyToOne
	@JoinColumn(name = "[account_id]")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne
	@JoinColumn(name = "[company_id]")
	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	@Column(name = "auth_type")
	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
}
