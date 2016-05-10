package com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml;
import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface CpdlmlDao extends AbstractReadWriteDao<CpdlmlEntity> {

	CpdlmlEntity getByDw(Date d, Company company, Integer cpid);

}
