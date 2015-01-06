package com.tbea.test.testWebProject.service.tbbzjqk;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.test.testWebProject.common.companys.Company;

public interface TBBZJQKService {

	String[] getTbbzjqkData(Date d, Company comp);

	Date getLatestDate();
	
}
