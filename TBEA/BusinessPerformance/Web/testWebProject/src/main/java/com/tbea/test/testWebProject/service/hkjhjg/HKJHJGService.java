package com.tbea.test.testWebProject.service.hkjhjg;

import java.sql.Date;

import com.tbea.test.testWebProject.common.Company;

public interface HKJHJGService {
	public String[][] getHkjhjgData(Date d, Company comp);

	public String[] getHkjhztData(Date d, Company comp);

	public String[]  getHkjhxzData(Date d, Company comp);
}
