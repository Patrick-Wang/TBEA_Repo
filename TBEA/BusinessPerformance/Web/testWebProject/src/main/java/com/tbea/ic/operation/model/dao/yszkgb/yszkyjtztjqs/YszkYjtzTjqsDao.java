package com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YszkYjtzTjqsDao extends AbstractReadWriteDao<YszkYjtzTjqsEntity> {

	List<YszkYjtzTjqsEntity> getByDate(Date ds, Date de, Company company);

}
