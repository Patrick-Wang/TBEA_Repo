package com.tbea.ic.operation.service.cpzlqk.pdacptjjg;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface PdacptjjgService {

	List<List<String>> getPdacptjjg(Date d, Company company, YDJDType yjType, List<Integer> zts);

	ErrorCode savePdacptjjg(Date d, JSONArray data, Company company);

	ErrorCode submitPdacptjjg(Date d, JSONArray data, Company company);

	List<List<String>> getPdacptjjgEntry(Date d, Company company);

	List<WaveItem> getWaveValues(Date d, Company company, List<Integer> zts);

	ErrorCode approvePdacptjjg(Date d, JSONArray data, Company company);

	ZBStatus getStatus(Date d, Company company);

	ErrorCode unapprovePdacptjjg(Date d, JSONArray data, Company company);

	List<WaveItem> getJdWaveValues(Date d, Company company, List<Integer> zts);

	ErrorCode updateStatus(Date d, Company company, ZBStatus zt);

}
