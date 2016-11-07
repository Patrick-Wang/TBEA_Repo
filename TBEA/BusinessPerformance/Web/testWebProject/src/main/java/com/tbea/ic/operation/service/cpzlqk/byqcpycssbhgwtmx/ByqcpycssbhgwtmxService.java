package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgwtmxService {

	List<List<String>> getByqcpycssbhgwtmx(Date d,
			YDJDType yjType,  List<Integer> zts);

	List<List<String>> getByqcpycssbhgwtmxEntry(Date d, Company company);

	ErrorCode approveByqcpycssbhgwtmx(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company);

	ErrorCode submitByqcpycssbhgwtmx(Date d, JSONArray data, Company company);

	ErrorCode saveByqcpycssbhgwtmx(Date d, JSONArray data, Company company);

	List<String> getZrlb();

	List<String> getBhglx();

	ErrorCode unapproveByqcpycssbhgwtmx(Date d, JSONArray data, Company company);

	List<List<String>> getByqcpycssbhgwtmx(Date d, YDJDType yjType,
			Company company,  List<Integer> zts);

	ErrorCode updateStatus(Date d, Company company, ZBStatus zt);




}
