package com.tbea.test.testWebProject.service.syhkjhzxqk;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.test.testWebProject.common.companys.Company;

public interface SYHKJHZXQKService {

	String[][] getSyhkjhzxqkData(Date d, Company comp);

	Date getLatestDate();

}
