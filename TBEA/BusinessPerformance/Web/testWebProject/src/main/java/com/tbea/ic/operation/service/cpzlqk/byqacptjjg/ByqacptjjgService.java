package com.tbea.ic.operation.service.cpzlqk.byqacptjjg;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqacptjjgService {

	List<List<String>> getByqacptjjg(Date d, Company company, YDJDType yjType);

	ErrorCode saveByqacptjjg(Date d, JSONArray data, Company company);

	ErrorCode submitByqacptjjg(Date d, JSONArray data, Company company);

	List<List<String>> getByqacptjjgEntry(Date d, Company company);

	List<WaveItem> getWaveValues(Date d, Company company);


}
