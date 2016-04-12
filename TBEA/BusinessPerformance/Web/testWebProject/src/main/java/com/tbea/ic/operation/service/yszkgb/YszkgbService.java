package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface YszkgbService {

	List<List<String>> getZmb(Date d, Company company);


}
