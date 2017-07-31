package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface YszkgbService {

	List<List<String>> getZmb(Date d, Company company);

	List<List<String>> getYszkzlbh(Date d, Company company);

	List<List<String>> getYszkkxxz(Date d, Company company);

	List<List<String>> getYszkyjtztjqs(Date d, Company company);

	List<List<String>> getYqyszcsys(Date d, Company company);

	List<List<String>> getYszkkxxzEntry(Date d, Company company);

	List<List<String>> getYqyszcsysEntry(Date d, Company company);

	List<List<String>> getYszkyjtztjqsEntry(Date d, Company company);

	ErrorCode saveYszkkxxz(Date d, Company company, JSONArray data);

	ErrorCode saveYqyszcsys(Date d, Company company, JSONArray data);

	ErrorCode saveYszkyjtztjqs(Date d, Company company, JSONArray data);

	ErrorCode submitYszkkxxz(Date d, Company company, JSONArray data);

	ErrorCode submitYqyszcsys(Date d, Company company, JSONArray data);

	ErrorCode submitYszkyjtztjqs(Date d, Company company, JSONArray data);

	ZBStatus getYszkkxxzStatus(Date d, Company comp);

	ZBStatus getYqyszcsysStatus(Date d, Company comp);

	ZBStatus getYszkyjtztjqsStatus(Date d, Company comp);

	void importZbmFromNC(Date d, List<Company> yszkgbComps);

	void importYszkzlbhFromNC(Date d, List<Company> yszkgbComps);

	List<String> getDashboardYszkzlbh(Date d);

	List<String> getDashboardYszkkxxz(Date d);

	List<List<String>> getDashboardYqyszcsys(Date d);

	List<List<String>> getDashboardYszkyjtztjqs(Date d);


}
