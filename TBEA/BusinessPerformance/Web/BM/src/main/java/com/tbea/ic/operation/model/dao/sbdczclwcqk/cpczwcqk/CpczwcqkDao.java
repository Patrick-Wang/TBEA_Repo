package com.tbea.ic.operation.model.dao.sbdczclwcqk.cpczwcqk;
import java.util.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.sbdczclwcqk.SbdczclwcqkType;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;
import com.tbea.ic.operation.model.entity.sbdczclwcqk.CpczwcqkEntity;
import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface CpczwcqkDao extends AbstractReadWriteDao<CpczwcqkEntity> {

	List<CpczwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type);

	List<CpczwcqkEntity> getByDate(Date d, Company company, SbdczclwcqkType type);
	
	List<CpczwcqkEntity> getByDate(Date ds, Date de, Company company, SbdczclwcqkType type, Integer cpId);

	CpczwcqkEntity getByDate(Date d, Company company, SbdczclwcqkType type, Integer cpId);
	
}
