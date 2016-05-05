package com.tbea.ic.operation.model.dao.cwyjsf.yjsf;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfEntity;



public interface YjsfDao extends AbstractReadWriteDao<YjsfEntity> {

	List<YjsfEntity> getByYear(Date d, Company company, Integer sz);

	YjsfEntity getByDate(Date d, Company company, Integer sz);

}
