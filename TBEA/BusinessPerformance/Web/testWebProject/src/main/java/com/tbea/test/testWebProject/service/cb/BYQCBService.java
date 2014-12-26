package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;

public interface BYQCBService {

	List<String[][]> getTbmx(Date date);

	List<String[][]> getZxmx(Date valueOf);

	List<String[][]> getWgmx(Date valueOf);


	boolean IsZxCompanyExist(Company company);
	boolean IsTbCompanyExist(Company company);
	boolean IsWgCompanyExist(Company company);
	
	String[][] getTbmx(Date date, Company comp);

	String[][] getZxmx(Date date, Company comp);

	String[][] getWgmx(Date date, Company comp);

	List<String[][]> getJtwg(Date date);
}
