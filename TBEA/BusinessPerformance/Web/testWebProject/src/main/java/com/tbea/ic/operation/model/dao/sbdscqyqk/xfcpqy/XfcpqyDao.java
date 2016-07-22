package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdscqyqk.SbdscqyqkType;
import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XfcpqyDao extends AbstractReadWriteDao<XfcpqyEntity> {
	
	List<XfcpqyEntity> getByDate(Date ds, Date de, Company company, SbdscqyqkType type);

	List<XfcpqyEntity> getByDate(Date d, Company company, SbdscqyqkType type);
	
	List<XfcpqyEntity> getByDate(Date ds, Date de, Company company, SbdscqyqkType type, Integer cpId);

	XfcpqyEntity getByDate(Date d, Company company, SbdscqyqkType type, Integer cpId);

	List<XfcpqyEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, SbdscqyqkType type, Integer cpId);

}
