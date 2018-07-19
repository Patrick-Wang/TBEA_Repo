package com.tbea.ic.operation.service.yszkrb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.Account;

public interface YSZKRBService {

	List<String[]> getYszkData(Date date, Account account);

	/**
	 * 获取应收账款日报表信息
	 * @param date
	 * @param account
	 * @return
	 */
	List<String[]> getYszkRbData(Date date, Account account);

}
