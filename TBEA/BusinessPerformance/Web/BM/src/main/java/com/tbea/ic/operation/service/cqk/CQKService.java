package com.tbea.ic.operation.service.cqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface CQKService {

	public String[][] getCqkData(Date d, Company comp);
	public String[][] getCompareData(Date d, Company comp);
	public Date getLatestDate();
	public String[][] getCqkData(Date d, List<Company> comps);
	public String[][] getCompareData(Date d, List<Company> comps);

}
