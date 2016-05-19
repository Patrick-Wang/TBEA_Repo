package com.tbea.ic.operation.model.dao.blhtdqqkhz;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.BLHTDQQKHZ;

public interface BLHTDQQKHZDao extends AbstractReadWriteDao<BLHTDQQKHZ> {

	List<BLHTDQQKHZ> getBlAfterDate(Calendar cal, Company comp);

	List<BLHTDQQKHZ> getBltbbh(Calendar cal, Company comp);

	BLHTDQQKHZ getLatestBl(Date d);

	List<BLHTDQQKHZ> getBlAfterDate(Calendar cal, List<Company> comps);

	List<BLHTDQQKHZ> getBltbbh(Calendar cal, List<Company> comps);

}
