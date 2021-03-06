package com.tbea.ic.operation.service.cb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface XLCBService {

	//List<String[][]> getTbmx(Date d);

	List<String[][]> getWgmx(Date d, Company company);

	boolean IsTbCompanyExist(Company comp);

	List<String[][]> getTbmx(Date date, Company comp);

	Date getLatestWgDate();

	List<Integer> getWgCompany();

	List<Integer> getTbCompany();

	Date getLatestTbDate();

}
