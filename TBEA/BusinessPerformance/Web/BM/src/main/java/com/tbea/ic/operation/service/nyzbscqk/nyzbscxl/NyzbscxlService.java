package com.tbea.ic.operation.service.nyzbscqk.nyzbscxl;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface NyzbscxlService {

	List<List<String>> getNyzbscxl(Date d, Company company);

	List<List<String>> getNyzbscxlEntry(Date d, Company company);

	ErrorCode saveNyzbscxl(Date d, JSONArray data, Company company);

	ErrorCode submitNyzbscxl(Date d, JSONArray data, Company company);


}
