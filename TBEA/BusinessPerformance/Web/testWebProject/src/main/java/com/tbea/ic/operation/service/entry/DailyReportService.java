package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface DailyReportService {

	boolean hasYszkAuthority(Account account);

	ErrorCode submitYszk(Account account, Date date, JSONArray jData);

	/**
	 * 录入提交操作
	 * @param account
	 * @param date
	 * @param jData
	 * @return
	 */
	ErrorCode submitYszkLr(Account account, Date date, JSONArray jData);

	List<String[]> getYszkData(Account account, Date date);

	/**
	 * 得到已经录入的月录入项
	 * @param account
	 * @param date
	 * @return
	 */
	List<String[]> getYszkLRData(Account account, Date date);

	boolean hasJYAnalysisEntryAuthority(Account account);

	boolean hasJYAnalysisLookupAuthority(Account account);
	
	boolean hasYSZKDialyLookupAuthority(Account account);
	
	boolean hasXJLDialyLookupAuthority(Account account);
	
	boolean hasJYEntryLookupAuthority(Account account);
}
