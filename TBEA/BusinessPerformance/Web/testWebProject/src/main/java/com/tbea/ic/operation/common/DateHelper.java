package com.tbea.ic.operation.common;

import java.sql.Date;
import java.util.Calendar;

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
		qntqJdStart = Util.toDate(cal);

		cal.setTime(qntqJdStart);
		cal.add(Calendar.MONTH, 2);
		qntqJdEnd = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		qntq = Util.toDate(cal);

		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		firstMonth = Util.toDate(cal);

		cal.setTime(qntq);
		cal.set(Calendar.MONTH, 0);
		qnfirstMonth = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		secondMonthinSeason = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.MONTH, 2);
		lastMonthinSeason = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		xjdFirstMonth = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.MONTH, 2);
		xjdSecondMonth = Util.toDate(cal);

		cal.setTime(date);
		cal.add(Calendar.MONTH, 3);
		xjdLastMonth = Util.toDate(cal);

		cal.setTime(qntq);
		cal.add(Calendar.MONTH, 1);
		qntqXjdFirstMonth = Util.toDate(cal);

		cal.setTime(qntq);
		cal.add(Calendar.MONTH, 2);
		qntqXjdSecondMonth = Util.toDate(cal);

		cal.setTime(qntq);
		cal.add(Calendar.MONTH, 3);
		qntqXjdLastMonth = Util.toDate(cal);

		cal.setTime(xjdLastMonth);
		cal.set(Calendar.MONTH, 0);
		xjdDnFirstMonth = Util.toDate(cal);

		cal.setTime(qntqXjdLastMonth);
		cal.set(Calendar.MONTH, 0);
		qntqXjdDnFirstMonth = Util.toDate(cal);
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
		return Util.toDate(cal);
	}
	
	public static Date getJdMiddle(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, (getJdCount(month) - 1) * 3 + 1);
		return Util.toDate(cal);
	}
	
	public static Date getJdEnd(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int month = cal.get(Calendar.MONTH) + 1;
		cal.set(Calendar.MONTH, (getJdCount(month) - 1) * 3 + 2);
		return Util.toDate(cal);
	}
	
	public Date getPreMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.cur);
		cal.add(Calendar.MONTH, -1);
		return Util.toDate(cal);
	}
}
