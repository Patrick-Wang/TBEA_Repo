package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface ChgbService {

	List<List<String>> getChzmb(Date d, Company company);
	
	List<List<String>> getChjykcb(Date d, Company company);
	
	List<List<String>> getChzlbhqk(Date d, Company company);
	
	List<List<String>> getChxzqk(Date d, Company company);
	
	List<List<String>> getChnych(Date d, Company company);
	
	List<List<String>> getChjykcbEntry(Date d, Company company);

	ErrorCode saveChjykcb(Date d, Company company, JSONArray data);

	ErrorCode submitChjykcb(Date d, Company company, JSONArray data);
	
	ZBStatus getChjykcbStatus(Date d, Company comp);
	
	List<List<String>> getChzlbhqkEntry(Date d, Company company);

	ErrorCode saveChzlbhqk(Date d, Company company, JSONArray data);

	ErrorCode submitChzlbhqk(Date d, Company company, JSONArray data);
	
	ZBStatus getChzlbhqkStatus(Date d, Company comp);
	
	List<List<String>> getChxzqkEntry(Date d, Company company);

	ErrorCode saveChxzqk(Date d, Company company, JSONArray data);

	ErrorCode submitChxzqk(Date d, Company company, JSONArray data);
	
	ZBStatus getChxzqkStatus(Date d, Company comp);

	void importZbmFromNC(Date d, List<Company> cOMPS);

	void importNychFromNC(Date d, List<Company> cOMPS);
}
