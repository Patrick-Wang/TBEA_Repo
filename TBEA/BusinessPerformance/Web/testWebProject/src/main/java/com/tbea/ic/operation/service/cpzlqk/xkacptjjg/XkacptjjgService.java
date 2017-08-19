package com.tbea.ic.operation.service.cpzlqk.xkacptjjg;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface XkacptjjgService {

	List<List<String>> getXkacptjjg(Date d, Company company, YDJDType yjType,  List<Integer> zts);

	ErrorCode saveXkacptjjg(Date d, JSONArray data, Company company);

	ErrorCode submitXkacptjjg(Date d, JSONArray data, Company company);

	List<List<String>> getXkacptjjgEntry(Date d, Company company);

	List<WaveItem> getWaveValues(Date d, Company company,  List<Integer> zts);

	ErrorCode approveXkacptjjg(Date d, JSONArray data, Company company, ZBStatus zbStatus);

	ErrorCode unapproveXkacptjjg(Date d, JSONArray data, Company company);

	List<WaveItem> getJdWaveValues(Date d, Company company,  List<Integer> zts);

	ErrorCode updateStatus(Date d, Company company, ZBStatus zt);

}
