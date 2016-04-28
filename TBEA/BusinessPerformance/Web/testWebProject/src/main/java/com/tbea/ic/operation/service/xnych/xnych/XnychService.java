package com.tbea.ic.operation.service.xnych.xnych;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface XnychService {

	List<List<String>> getXnych(Date d, Company company);

	List<List<String>> getXnychEntry(Date d, Company company);

	ErrorCode saveXnych(Date d, JSONArray data, Company company);

	ErrorCode submitXnych(Date d, JSONArray data, Company company);


}
