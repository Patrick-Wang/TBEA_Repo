package com.tbea.ic.operation.service.tbbzjqk;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;

public interface TBBZJQKService {

	String[] getTbbzjqkData(Date d, Company comp);

	Date getLatestDate();
	
}
