package com.tbea.ic.operation.model.dao.yqkbhqs;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.QYZJK;
import com.tbea.ic.operation.model.entity.YQKBHQS;

public interface YQKBHQSDao extends AbstractReadWriteDao<YQKBHQS> {

	List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cal, Company comp);

	YQKBHQS getLatestDate();

	List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cal, List<Company> subComps);

}
