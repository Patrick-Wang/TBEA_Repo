package com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk;
import java.util.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpclwcqkEntity;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface CpclwcqkDao extends AbstractReadWriteDao<CpclwcqkEntity> {
	
	List<CpclwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type);

	List<CpclwcqkEntity> getByDate(Date d, Company company, SbdczclwcqkType type);
	
	List<CpclwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type, Integer cpId);

	CpclwcqkEntity getByDate(Date d, Company company, SbdczclwcqkType type, Integer cpId);
}
