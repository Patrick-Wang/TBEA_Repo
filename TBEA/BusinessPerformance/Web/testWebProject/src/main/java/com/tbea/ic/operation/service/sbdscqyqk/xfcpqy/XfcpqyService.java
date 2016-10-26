package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;

public interface XfcpqyService {

	List<List<String>> getXfcpqy(Date d, Company company, SbdscqyqkType type);
	
	List<List<String>> getXfcpqyEntry(Date d, Company company, SbdscqyqkType type);

	ErrorCode saveXfcpqy(Date d, Company company, SbdscqyqkType type, JSONArray data);

	ErrorCode submitXfcpqy(Date d, Company company, SbdscqyqkType type, JSONArray data);
	
	ZBStatus getXfcpqyStatus(Date d, Company comp, SbdscqyqkType type);

	void importCpqy(Date d);

}
