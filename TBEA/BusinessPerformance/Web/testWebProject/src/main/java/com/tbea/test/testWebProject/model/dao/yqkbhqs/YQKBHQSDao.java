package com.tbea.test.testWebProject.model.dao.yqkbhqs;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.QYZJK;
import com.tbea.test.testWebProject.model.entity.YQKBHQS;

public interface YQKBHQSDao extends AbstractReadWriteDao<YQKBHQS> {

	List<YQKBHQS> getYqkbhqsOfThisYear(Calendar cal, Company comp);

	YQKBHQS getLatestDate();

}
