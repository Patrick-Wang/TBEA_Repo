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
	
	public DateHelper(Date date) {
		this.cur = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -(cal.get(Calendar.MONTH) + 1) % 3 + 1);
		jdStart = Util.toDate(cal);
		
		cal.setTime(jdStart);
		cal.add(Calendar.YEAR, -1);
		qntqJdStart = Util.toDate(cal);
		
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		qntq = Util.toDate(cal);
		
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		firstMonth = Util.toDate(cal);
		
		cal.setTime(qntq);
		cal.set(Calendar.MONTH, 0);
		qnfirstMonth = Util.toDate(cal);
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
	
}