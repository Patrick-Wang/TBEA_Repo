package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgwtmxService {

	List<List<String>> getByqcpycssbhgwtmx(Date d,
			YDJDType yjType, ByqBhgType bhgType);

	List<List<String>> getByqcpycssbhgwtmxEntry(Date d, Company company, ByqBhgType bhgType);

	ErrorCode approveByqcpycssbhgwtmx(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company, ByqBhgType bhgType);

	ErrorCode submitByqcpycssbhgwtmx(Date d, JSONArray data, Company company, ByqBhgType bhgType);

	ErrorCode saveByqcpycssbhgwtmx(Date d, JSONArray data, Company company, ByqBhgType bhgType);

	List<String> getZrlb();

	List<String> getBhglx();




}
