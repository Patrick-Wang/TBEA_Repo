package com.tbea.ic.operation.service.sbdczclwcqk.cpczwcqk;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;

public interface CpczwcqkService {

	List<List<String>> getCpczwcqk(Date d, Company company, SbdczclwcqkType type);
	
	List<List<String>> getCpczwcqkEntry(Date d, Company company, SbdczclwcqkType type);

	ErrorCode saveCpczwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);

	ErrorCode submitCpczwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);
	
	ZBStatus getCpczwcqkStatus(Date d, Company comp, SbdczclwcqkType type);
	
}
