package com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgwtmx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface PdcpycssbhgwtmxService {

	List<List<String>> getPdcpycssbhgwtmx(Date d,
			YDJDType yjType, List<Integer> zts);

	List<List<String>> getPdcpycssbhgwtmxEntry(Date d, Company company);

	ErrorCode approvePdcpycssbhgwtmx(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company);

	ErrorCode submitPdcpycssbhgwtmx(Date d, JSONArray data, Company company);

	ErrorCode savePdcpycssbhgwtmx(Date d, JSONArray data, Company company);

	List<String> getZrlb();

	List<String> getBhglx();

	ErrorCode unapprovePdcpycssbhgwtmx(Date d, JSONArray data, Company company);

	List<List<String>> getPdcpycssbhgwtmx(Date d, YDJDType yjType,
			Company compan, List<Integer> zts);

	ErrorCode updateStatus(Date d, Company company, ZBStatus zt);




}
