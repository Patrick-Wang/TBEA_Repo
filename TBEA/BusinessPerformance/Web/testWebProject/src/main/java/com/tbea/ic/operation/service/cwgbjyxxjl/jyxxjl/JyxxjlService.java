package com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface JyxxjlService {

	List<List<String>> getJyxxjlND(Date d, Company company);
	
	List<List<String>> getJyxxjlYD(Date d, Company company);
	
	List<List<String>> getJyxxjlEntry(Date d, Company company);

	ErrorCode saveJyxxjl(Date d, Company company, JSONArray data);

	ErrorCode submitJyxxjl(Date d, Company company, JSONArray data);
	
	ZBStatus getJyxxjlStatus(Date d, Company comp);

	List<String> getJyxxjlYDSJ(Date d, Company company);
}
