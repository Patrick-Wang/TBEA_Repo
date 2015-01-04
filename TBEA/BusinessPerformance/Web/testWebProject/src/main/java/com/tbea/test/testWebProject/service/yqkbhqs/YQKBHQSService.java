package com.tbea.test.testWebProject.service.yqkbhqs;

import java.sql.Date;

import com.tbea.test.testWebProject.common.companys.Company;

public interface YQKBHQSService {

	public String[][] getYqkbhqsData(Date d, Company comp);

	public Date getLatestDate();

}
