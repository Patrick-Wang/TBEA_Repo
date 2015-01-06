package com.tbea.test.testWebProject.service.hkjhjg;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.test.testWebProject.common.companys.Company;

public interface HKJHJGService {
	public String[][] getHkjhjgData(Date d, Company comp);

	public String[] getHkjhztData(Date d, Company comp);

	public String[]  getHkjhxzData(Date d, Company comp);

	public Date getLatestDate();
}
