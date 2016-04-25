package com.tbea.ic.operation.service.cbfx.dmcbfx;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface DmcbfxService {

	List<List<String>> getDmcbfx(Date d, Company company);

	List<List<String>> getDmcbfxEntry(Date d, Company company);

	ErrorCode saveDmcbfx(Date d, JSONArray data, Company company);

	ErrorCode submitDmcbfx(Date d, JSONArray data, Company company);


}
