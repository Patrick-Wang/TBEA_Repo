package com.tbea.ic.operation.model.dao.xnych.xnych;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.xnych.XnychEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XnychDao extends AbstractReadWriteDao<XnychEntity> {

	List<XnychEntity> getByDate(Date d, Company company);

}
