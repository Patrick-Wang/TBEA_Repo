package com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface XlbhgcpmxService {

	List<List<String>> getXlbhgcpmx(Date d);

	List<List<String>> getXlbhgcpmxEntry(Date d, Company company);

	ErrorCode approveXlbhgcpmx(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company);

	ErrorCode submitXlbhgcpmx(Date d, JSONArray data, Company company);

	ErrorCode saveXlbhgcpmx(Date d, JSONArray data, Company company);

	List<String> getZrlb();

	List<String> getBhglx();

	ErrorCode unapproveXlbhgcpmx(Date d, JSONArray data, Company company);


}
