package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface DailyReportService {

	boolean hasYszkAuthority(Account account);

	ErrorCode submitYszk(Account account, Date date, JSONArray jData);

	List<String[]> getYszkData(Account account, Date date);

}
