package com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk;
import java.util.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.ClylwcqkEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ClylwcqkDao extends AbstractReadWriteDao<ClylwcqkEntity> {
	List<ClylwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type);

	List<ClylwcqkEntity> getByDate(Date d, Company company, SbdczclwcqkType type);
	
	List<ClylwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type, Integer cpId);

	ClylwcqkEntity getByDate(Date d, Company company, SbdczclwcqkType type, Integer cpId);
}
