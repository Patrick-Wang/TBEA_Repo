package com.tbea.ic.operation.model.dao.chgb.chjykc;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ChJykcDao extends AbstractReadWriteDao<ChJykcEntity> {

	List<ChJykcEntity> getByDate(Date d, Company company);

	List<ChJykcEntity> getSumByDate(Date d, List<Company> subCompanies);
}
