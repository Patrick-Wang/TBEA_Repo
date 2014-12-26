package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;

public interface BYQCBService {

	List<String[][]> getTbmx(Date date);

	List<String[][]> getZxmx(Date valueOf);

	List<String[][]> getWgmx(Date valueOf);

	String[][] getJtwg(Date d);

	boolean IsCompanyExist(Company company);

	String[][] getTbmx(Date valueOf, Company comp);

//	String[][] getJttb(Date date);
//
//	String[][] getGstb(Date date);

}
