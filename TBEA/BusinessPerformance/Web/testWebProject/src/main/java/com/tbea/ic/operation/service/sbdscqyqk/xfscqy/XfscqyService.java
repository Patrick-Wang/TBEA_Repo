package com.tbea.ic.operation.service.sbdscqyqk.xfscqy;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface XfscqyService {

	List<List<String>> getXfscqy(Date d, Company company);

	List<List<String>> getXfscqyEntry(Date d, Company company);

	ErrorCode saveXfscqy(Date d, JSONArray data, Company company);

	ErrorCode submitXfscqy(Date d, JSONArray data, Company company);

	void importScqy(Date d);


}
