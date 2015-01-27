package com.tbea.ic.operation.service.blhtdqqkhz;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.BLHTDQQKHZ;

public interface BLHTDQQKHZService {

	public BLHTDQQKHZ getBLById(int id);
	public String[][] getBlhtdqqk(Date date, Company comp);
	public String[][] getBlyeqs(Date date, Company comp);
	public Date getLatestDate();

}
