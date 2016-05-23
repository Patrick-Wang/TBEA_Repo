package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;

public interface WlyddmlspcsService {

	List<List<String>> getWlyddmlspcs(Date d, Company company, WlyddType type);
	
	List<List<String>> getWlyddmlspcsEntry(Date d, Company company, WlyddType type);

	ErrorCode saveWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data);

	ErrorCode submitWlyddmlspcs(Date d, Company company, WlyddType type, JSONArray data);
	
	ZBStatus getWlyddmlspcsStatus(Date d, Company comp, WlyddType type);
}
