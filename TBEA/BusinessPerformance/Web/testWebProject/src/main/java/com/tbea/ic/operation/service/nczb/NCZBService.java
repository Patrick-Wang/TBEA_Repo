package com.tbea.ic.operation.service.nczb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface NCZBService {

	List<String[]> getGSZB(Date d, List<Company> companies);

}
