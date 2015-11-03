package com.tbea.ic.operation.model.entity.jygk;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tbea.ic.operation.model.entity.jygk.DWXX;

public class YSDAILYPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "[dialy_date]")
	Date date;

	@Column(name = "[companyId]")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dwid")
	DWXX dwxx;

	public DWXX getDwxx() {
		return dwxx;
	}

	public void setDwxx(DWXX dwxx) {
		this.dwxx = dwxx;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return dwxx.hashCode() + date.hashCode();
	}

	
	private boolean equalDate(Date d1, Date d2){
		if (d1 == d2){
			return true;
		}
		
		if (d1 != null && d2 != null && d1.equals(d2)){
			return true;
		}
		
		return false;
	}
	
	private boolean equalDwxx(DWXX d1, DWXX d2){
		if (d1 == d2){
			return true;
		}
		
		if (d1 != null && d2 != null && d1.getId() == d2.getId()){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final YSDAILYPK other = (YSDAILYPK) obj;

		if (!equalDate(date, other.date)) {
			return false;
		} 
		
		if (!equalDwxx(dwxx, other.dwxx)) {
			return false;
		} 
		
		return true;

	}

}
