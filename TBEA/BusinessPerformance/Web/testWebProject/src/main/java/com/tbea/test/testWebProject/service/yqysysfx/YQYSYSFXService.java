package com.tbea.test.testWebProject.service.yqysysfx;

import java.sql.Date;

import com.tbea.test.testWebProject.common.Company;

public interface YQYSYSFXService {

	String[][] getYqysysfxData(Date d, Company comp);

}