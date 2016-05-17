package com.tbea.ic.operation.service.cbfx.nymyywmlfx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface NymyywmlfxService {

	List<List<String>> getNymyywmlfx(Date d, Company company);

	ErrorCode saveNymyywmlfx(Date d, JSONArray data, Company company);

	List<List<String>> getNymyywmlfxEntry(Date d, Company company);

	ErrorCode submitNymyywmlfx(Date d, JSONArray data, Company company);

}
