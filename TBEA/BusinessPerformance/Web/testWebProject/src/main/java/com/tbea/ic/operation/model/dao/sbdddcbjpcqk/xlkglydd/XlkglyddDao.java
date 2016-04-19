package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.XlkglyddEntity;



public interface XlkglyddDao extends AbstractReadWriteDao<XlkglyddEntity> {

	List<XlkglyddEntity> getByDate(Date d, WlyddType type, Company company);

}
