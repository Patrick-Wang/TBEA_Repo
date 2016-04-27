package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface XfcpqyService {

	List<List<String>> getXfcpqy(Date d, Company company);

	List<List<String>> getXfcpqyEntry(Date d, Company company);

	ErrorCode saveXfcpqy(Date d, JSONArray data, Company company);

	ErrorCode submitXfcpqy(Date d, JSONArray data, Company company);


}
