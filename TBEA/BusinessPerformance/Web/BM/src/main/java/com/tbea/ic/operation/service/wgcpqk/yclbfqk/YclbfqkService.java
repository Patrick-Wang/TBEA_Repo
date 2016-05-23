package com.tbea.ic.operation.service.wgcpqk.yclbfqk;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface YclbfqkService {

	List<List<String>> getYclbfqk(Date d, Company company);

	ErrorCode submitYclbfqk(Date d, JSONArray data, Company company);

	ErrorCode saveYclbfqk(Date d, JSONArray data, Company company);

	List<List<String>> getYclbfqkEntry(Date d, Company company);


}
