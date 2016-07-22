package com.tbea.ic.operation.service.yszkrb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.Account;

public interface YSZKRBService {

	List<String[]> getYszkData(Date date, Account account);

}
