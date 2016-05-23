package com.tbea.ic.operation.service.sbdddcbjpcqk;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;

public interface SbdddcbjpcqkService {

	List<List<String>> getByqkglydd(Date d, WlyddType type, Company company);

	List<List<String>> getXlkglydd(Date d, WlyddType type, Company company);

	List<List<String>> getByqkglyddEntry(Date d, WlyddType type, Company company);

	List<List<String>> getXlkglyddEntry(Date d, WlyddType type, Company company);

	ErrorCode saveXlkglydd(Date d, WlyddType type, JSONArray data, Company company);

	ErrorCode saveByqkglydd(Date d, WlyddType type, JSONArray data, Company company);

	ErrorCode submitByqkglydd(Date d, WlyddType type, JSONArray data, Company company);

	ErrorCode submitXlkglydd(Date d, WlyddType type, JSONArray data, Company company);

	List<String> getByqCplb();

	List<String> getXlCplb();


}
