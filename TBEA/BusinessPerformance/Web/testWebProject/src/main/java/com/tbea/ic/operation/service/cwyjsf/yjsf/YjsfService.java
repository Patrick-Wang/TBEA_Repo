package com.tbea.ic.operation.service.cwyjsf.yjsf;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface YjsfService {

	List<List<String>> getYjsf(Date d, Company company);


}
