package com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;

public interface CpclwcqkService {
	
	List<List<String>> getCpclwcqk(Date d, Company company, SbdczclwcqkType type);
	
	List<List<String>> getCpclwcqkEntry(Date d, Company company, SbdczclwcqkType type);

	ErrorCode saveCpclwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);

	ErrorCode submitCpclwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);
	
	ZBStatus getCpclwcqkStatus(Date d, Company comp, SbdczclwcqkType type);

}
