package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface CpdlmlService {

	ErrorCode submitCpdlml(Date d, JSONArray data, Company company);

	List<List<String>> getCpdlml(Date d, Company company);

	ErrorCode saveCpdlml(Date d, JSONArray data, Company company);

	List<List<String>> getCpdlmlEntry(Date d, Company company);


}
 