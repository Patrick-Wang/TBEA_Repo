package com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfscqyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XfscqyDao extends AbstractReadWriteDao<XfscqyEntity> {

	List<XfscqyEntity> getByDate(Date d, Company company);

	XfscqyEntity getByDate(Date d, Company company, int hy);

}
