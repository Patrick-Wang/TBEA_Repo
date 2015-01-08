package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;

public interface BYQCBService {

	List<String[][]> getTbmx(Date date);

	List<String[][]> getZxmx(Date valueOf);

	List<String[][]> getWgmx(Date valueOf);


	boolean IsZxCompanyExist(Company company);
	boolean IsTbCompanyExist(Company company);
	boolean IsWgCompanyExist(Company company);
	
	String[][] getTbmx(Company comp);

	String[][] getZxmx(Company comp);

	String[][] getWgmx(Company comp);

	List<String[][]> getJtwg(Date date);

	Date getLatestWgDate();

}
