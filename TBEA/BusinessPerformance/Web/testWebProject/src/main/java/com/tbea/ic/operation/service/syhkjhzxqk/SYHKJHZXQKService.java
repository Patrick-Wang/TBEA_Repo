package com.tbea.ic.operation.service.syhkjhzxqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface SYHKJHZXQKService {
	
	Date getLatestDate();

	String[][] getSyhkjhzxqkData(Date d, Company comp);

	String[][] getHkjhzxqkXjData(Date d, Company comp);

	String[][] getSyhkjhzxqkData(Date d, List<Company> comps);

	String[][] getHkjhzxqkXjData(Date d, List<Company> comps);
}
