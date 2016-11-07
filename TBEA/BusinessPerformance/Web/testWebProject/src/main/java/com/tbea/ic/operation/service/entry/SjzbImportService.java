package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.Map;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.companys.Company;

public interface SjzbImportService {

	Map<Company, JSONArray> getHBSjzb(Date d);

	Map<Company, JSONArray> getDLSjzb(Date d);
}
