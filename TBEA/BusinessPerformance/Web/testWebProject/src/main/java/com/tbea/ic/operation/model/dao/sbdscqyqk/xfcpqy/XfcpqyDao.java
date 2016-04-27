package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XfcpqyDao extends AbstractReadWriteDao<XfcpqyEntity> {

	List<XfcpqyEntity> getByDate(Date date, Date d, Company company, int cp);

}
