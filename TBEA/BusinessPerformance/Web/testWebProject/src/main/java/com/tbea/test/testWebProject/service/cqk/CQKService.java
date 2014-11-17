package com.tbea.test.testWebProject.service.cqk;

import java.sql.Date;

import com.tbea.test.testWebProject.common.Company;

public interface CQKService {

	public String[][] getCqkData(Date d, Company comp);
	public String[][] getCompareData(Date d, Company comp);
	public boolean importCQK();

}
