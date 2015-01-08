package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;

public interface XLCBService {

	List<String[][]> getTbmx(Date d);

	List<String[][]> getWgmx(Date d);

	boolean IsTbCompanyExist(Company comp);

	List<String[]> getTbmx(Date date, Company comp);

	Date getLatestWgDate();

}
