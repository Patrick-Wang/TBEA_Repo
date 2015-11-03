package com.tbea.ic.operation.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tbea.ic.operation.model.entity.jygk.DWXX;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

@Entity
@IdClass(YSDAILYPK.class)
@Table(name = "jygk_ysdaily")
public class YSDAILY extends AbstractReadWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "[dialy_date]")
	Date date;

	@Id
	@Column(name = "[companyId]")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	DWXX dwxx;

	@Column(name = "[withdrawal_funds_target_month]")
	Double withdrawalFundsTargetMonth;
	@Column(name = "[withdrawal_plan]")
	Double withdrawalPlan;
	@Column(name = "[withdrawal_today]")
	Double withdrawalToday;
	@Column(name = "[kjys_withdrawal_today]")
	Double kjysWithdrawalToday;
	@Column(name = "[qbbc_money]")
	Double qbbcMoney;
	@Column(name = "[zqbc_money]")
	Double zqbcMoney;
	@Column(name = "[balance_account]")
	Double balanceAccount;

}
