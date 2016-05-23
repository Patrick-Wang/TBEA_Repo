package com.tbea.ic.operation.service.cwyjsf.yjsfljs;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface YjsfljsService {

	List<List<String>> getYjsfljs(Date d, Company company);

}
