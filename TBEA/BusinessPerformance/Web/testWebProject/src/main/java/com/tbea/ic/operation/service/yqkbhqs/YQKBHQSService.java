package com.tbea.ic.operation.service.yqkbhqs;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public interface YQKBHQSService {

	public String[][] getYqkbhqsData(Date d, Company comp);

	public Date getLatestDate();

	public String[][] getYqkbhqsData(Date d, List<Company> subCompanys);

}
