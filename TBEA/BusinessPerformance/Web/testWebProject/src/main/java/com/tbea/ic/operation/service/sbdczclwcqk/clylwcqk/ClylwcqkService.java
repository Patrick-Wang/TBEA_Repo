package com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;

public interface ClylwcqkService {

	List<List<String>> getClylwcqk(Date d, Company company, SbdczclwcqkType type);
	
	List<List<String>> getClylwcqkEntry(Date d, Company company, SbdczclwcqkType type);

	ErrorCode saveClylwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);

	ErrorCode submitClylwcqk(Date d, Company company, SbdczclwcqkType type, JSONArray data);
	
	ZBStatus getClylwcqkStatus(Date d, Company comp, SbdczclwcqkType type);
}
