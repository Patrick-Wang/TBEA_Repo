package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "jygk_ysdaily")
public class YSDAILY implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	YSDAILYPK key;
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

	public YSDAILYPK getKey() {
		return key;
	}

	public void setKey(YSDAILYPK key) {
		this.key = key;
	}

	public Double getWithdrawalFundsTargetMonth() {
		return withdrawalFundsTargetMonth;
	}

	public void setWithdrawalFundsTargetMonth(Double withdrawalFundsTargetMonth) {
		this.withdrawalFundsTargetMonth = withdrawalFundsTargetMonth;
	}

	public Double getWithdrawalPlan() {
		return withdrawalPlan;
	}

	public void setWithdrawalPlan(Double withdrawalPlan) {
		this.withdrawalPlan = withdrawalPlan;
	}

	public Double getWithdrawalToday() {
		return withdrawalToday;
	}

	public void setWithdrawalToday(Double withdrawalToday) {
		this.withdrawalToday = withdrawalToday;
	}

	public Double getKjysWithdrawalToday() {
		return kjysWithdrawalToday;
	}

	public void setKjysWithdrawalToday(Double kjysWithdrawalToday) {
		this.kjysWithdrawalToday = kjysWithdrawalToday;
	}

	public Double getQbbcMoney() {
		return qbbcMoney;
	}

	public void setQbbcMoney(Double qbbcMoney) {
		this.qbbcMoney = qbbcMoney;
	}

	public Double getZqbcMoney() {
		return zqbcMoney;
	}

	public void setZqbcMoney(Double zqbcMoney) {
		this.zqbcMoney = zqbcMoney;
	}

	public Double getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(Double balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

}
