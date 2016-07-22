package com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wlydd.WlyddType;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.wlydd.wlyddmslspcs.WlyddmlspcsEntity;


public interface WlyddmlspcsDao extends AbstractReadWriteDao<WlyddmlspcsEntity> {

	List<WlyddmlspcsEntity> getByDate(Date ds, Date de, Company company, WlyddType type);

	List<WlyddmlspcsEntity> getByDate(Date d, Company company, WlyddType type);
	
	List<WlyddmlspcsEntity> getByDate(Date ds, Date de, Company company, WlyddType type, Integer cpId);

	WlyddmlspcsEntity getByDate(Date d, Company company, WlyddType type, Integer cpId);

	List<WlyddmlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> subCompanies, WlyddType type, Integer cpId);

	List<WlyddmlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, WlyddType byq,
			WlyddType xl, Integer cpId);
}
