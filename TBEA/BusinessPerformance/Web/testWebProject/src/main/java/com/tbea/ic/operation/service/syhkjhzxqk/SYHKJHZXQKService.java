package com.tbea.ic.operation.service.syhkjhzxqk;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;

public interface SYHKJHZXQKService {

	String[][] getSyhkjhzxqkData(Date d, Company comp);

	Date getLatestDate();

	String[][] getHkjhzxqkXjData(Date d, Company comp);

}
