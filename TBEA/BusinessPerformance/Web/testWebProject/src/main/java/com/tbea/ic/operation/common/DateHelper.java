package com.tbea.ic.operation.common;

import java.sql.Date;
import java.util.Calendar;

import com.util.tools.DateUtil;

public class DateHelper {
	private Date cur;
	private Date qntq;
	private Date jdStart;
	private Date qntqJdStart;
	private Date firstMonth;
	private Date qnfirstMonth;
	private Date secondMonthinSeason;
	private Date lastMonthinSeason;
	private Date qntqJdEnd;
	private Date xjdFirstMonth;
	private Date xjdSecondMonth;
	private Date xjdLastMonth;
	private Date qntqXjdFirstMonth;
	private Date qntqXjdSecondMonth;
	private Date qntqXjdLastMonth;
	private Date xjdDnFirstMonth;
	private Date qntqXjdDnFirstMonth;
	
	
	public DateHelper(Date date) {
		this.cur = date;
		Calendar cal = Calendar.getInstance();
		jdStart = getJdStart(date);

		
		
		cal.setTime(jdStart);
		cal.add(Calendar.YEAR, -1);
		qntqJdStart = DateUtil.toDate(cal);

		cal.setTime(qntqJdStart);
		cal.add(Calendar.MONTH, 2);
		qntqJdEnd = DateUtil.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		qntq = DateUtil.toDate(cal);

		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		firstMonth = DateUtil.toDate(cal);

		cal.setTime(qntq);
		cal.set(Calendar.MONTH, 0);
		qnfirstMonth = DateUtil.toDate(cal);

		cal.setTime(jdStart);
		cal.add(Calendar.MONTH, 1);
		secondMonthinSeason = DateUtil.toDate(cal);

		cal.setTime(jdStart);
		cal.add(Calendar.MONTH, 2);
		lastMonthinSeason = DateUtil.toDate(cal);

		cal.setTime(lastMonthinSeason);
		cal.add(Calendar.MONTH, 1);
		xjdFirstMonth = DateUtil.toDate(cal);

		cal.setTime(lastMonthinSeason);
		cal.add(Calendar.MONTH, 2);
		xjdSecondMonth = DateUtil.toDate(cal);

		cal.setTime(lastMonthinSeason);
		cal.add(Calendar.MONTH, 3);
		xjdLastMonth = DateUtil.toDate(cal);

		cal.setTime(qntqJdStart);
		cal.add(Calendar.MONTH, 3);
		qntqXjdFirstMonth = DateUtil.toDate(cal);

		cal.setTime(qntqXjdFirstMonth);
		cal.add(Calendar.MONTH, 1);
		qntqXjdSecondMonth = DateUtil.toDate(cal);

		cal.setTime(qntqXjdSecondMonth);
		cal.add(Calendar.MONTH, 1);
		qntqXjdLastMonth = DateUtil.toDate(cal);

		cal.setTime(xjdLastMonth);
		cal.set(Calendar.MONTH, 0);
		xjdDnFirstMonth = DateUtil.toDate(cal);

		cal.setTime(qntqXjdLastMonth);
		cal.set(Calendar.MONTH, 0);
		qntqXjdDnFirstMonth = DateUtil.toDate(cal);
	}

	public Date getSecondMonthinSeason() {
		return secondMonthinSeason;
	}

	public Date getLastMonthinSeason() {
		return lastMonthinSeason;
	}

	public Date getQntqJdEnd() {
		return qntqJdEnd;
	}

	/**
	 * @return the cur
	 */
	public Date getCur() {
		return cur;
	}

	/**
	 * @return the qntq
	 */
	public Date getQntq() {
		return qntq;
	}

	/**
	 * @return the jdStart
	 */
	public Date getJdStart() {
		return jdStart;
	}

	/**
	 * @return the qntqJdStart
	 */
	public Date getQntqJdStart() {
		return qntqJdStart;
	}

	/**
	 * @return the firstMonth
	 */
	public Date getFirstMonth() {
		return firstMonth;
	}

	/**
	 * @return the qnfirstMonth
	 */
	public Date getQnfirstMonth() {
		return qnfirstMonth;
	}

	public Date getXjdFirstMonth() {
		return xjdFirstMonth;
	}

	public Date getXjdSecondMonth() {
		return xjdSecondMonth;
	}

	public Date getXjdLastMonth() {
		return xjdLastMonth;
	}

	public Date getQntqXjdFirstMonth() {
		return qntqXjdFirstMonth;
	}

	public Date getQntqXjdSecondMonth() {
		return qntqXjdSecondMonth;
	}

	public Date getQntqXjdLastMonth() {
		return qntqXjdLastMonth;
	}

	public Date getXjdDnFirstMonth() {
		return xjdDnFirstMonth;
	}

	public Date getQntqXjdDnFirstMonth() {
		return qntqXjdDnFirstMonth;
	}
	
	public static int getJdCount(int month){
		return (month - 1) / 3 + 1;
	}
	
	public static Date getJdStart(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, (getJdCount(month) - 1) * 3);
		return DateUtil.toDate(cal);
	}
	
	public static Date getJdMiddle(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, (getJdCount(month) - 1) * 3 + 1);
		return DateUtil.toDate(cal);
	}
	
	public static Date getJdEnd(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, (getJdCount(month) - 1) * 3 + 2);
		return DateUtil.toDate(cal);
	}
	
	public Date getPreMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.cur);
		cal.add(Calendar.MONTH, -1);
		return DateUtil.toDate(cal);
	}
	
	public static Date getFirstDayOfMonth(Date date){
		Calendar calTmp = Calendar.getInstance();
		calTmp.setTime(date);
		calTmp.set(Calendar.DAY_OF_MONTH, 1);
		return DateUtil.toDate(calTmp);
	}
}
