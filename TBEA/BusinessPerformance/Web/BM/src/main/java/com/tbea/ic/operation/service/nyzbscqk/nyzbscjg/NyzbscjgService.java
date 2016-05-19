package com.tbea.ic.operation.service.nyzbscqk.nyzbscjg;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface NyzbscjgService {

	List<List<String>> getNyzbscjg(Date d, Company company);

	List<List<String>> getNyzbscjgEntry(Date d, Company company);

	ErrorCode saveNyzbscjg(Date d, JSONArray data, Company company);

	ErrorCode submitNyzbscjg(Date d, JSONArray data, Company company);


}
