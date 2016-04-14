package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface ChgbService {

	List<List<String>> getChzmb(Date d, Company company);
	
	List<List<String>> getChjykcb(Date d, Company company);
	
	List<List<String>> getChzlbhqk(Date d, Company company);
}
