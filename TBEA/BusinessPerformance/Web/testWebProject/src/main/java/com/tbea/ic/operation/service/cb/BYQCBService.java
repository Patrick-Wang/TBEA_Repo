package com.tbea.ic.operation.service.cb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface BYQCBService {

	List<String[][]> getTbmx(Date date);

	List<String[][]> getZxmx(Date valueOf);

	List<String[][]> getWgmx(Date valueOf);


	List<Integer> getZxCompany();
	boolean IsTbCompanyExist(Company company);
	List<Integer> getWgCompany();
	
	String[][] getTbmx(Company comp);

	String[][] getZxmx(Company comp);

	String[][] getWgmx(Company comp);

	List<String[][]> getJtwg(Date date);

	Date getLatestWgDate();

	List<Integer> getTbCompany();

	

}
