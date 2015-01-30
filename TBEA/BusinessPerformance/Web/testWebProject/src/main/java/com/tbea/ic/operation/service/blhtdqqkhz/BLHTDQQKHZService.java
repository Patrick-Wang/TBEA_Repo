package com.tbea.ic.operation.service.blhtdqqkhz;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.BLHTDQQKHZ;

public interface BLHTDQQKHZService {

	public String[][] getBlhtdqqk(Date date, Company comp);
	public String[][] getBlyeqs(Date date, Company comp);
	public Date getLatestDate();
	public String[][] getBlyeqs(Date d, List<Company> comps);
	public String[][] getBlhtdqqk(Date d, List<Company> comps);

}
