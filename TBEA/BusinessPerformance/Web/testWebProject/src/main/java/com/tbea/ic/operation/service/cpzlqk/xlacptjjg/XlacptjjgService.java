package com.tbea.ic.operation.service.cpzlqk.xlacptjjg;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface XlacptjjgService {

	List<List<String>> getXlacptjjg(Date d, Company company, YDJDType yjType);

	List<List<String>> getXlacptjjgEntry(Date d, Company company);

	ErrorCode saveXlacptjjg(Date d, JSONArray data, Company company);

	ErrorCode submitXlacptjjg(Date d, JSONArray data, Company company);

	List<WaveItem> getWaveValues(Date d, Company company);

	ErrorCode approveXlacptjjg(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company);


}
