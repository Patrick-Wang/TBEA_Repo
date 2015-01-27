package com.tbea.ic.operation.service.yqkbhqs;

import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;

public interface YQKBHQSService {

	public String[][] getYqkbhqsData(Date d, Company comp);

	public Date getLatestDate();

}
