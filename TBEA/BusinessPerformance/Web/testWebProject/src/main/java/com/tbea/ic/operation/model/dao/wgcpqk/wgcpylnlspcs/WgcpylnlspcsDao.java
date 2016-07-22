package com.tbea.ic.operation.model.dao.wgcpqk.wgcpylnlspcs;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.wgcpqk.wgcpylnlspcs.WgcpylnlspcsEntity;


public interface WgcpylnlspcsDao extends AbstractReadWriteDao<WgcpylnlspcsEntity> {

	List<WgcpylnlspcsEntity> getByDate(Date ds, Date de, Company company, WgcpqkType type);

	List<WgcpylnlspcsEntity> getByDate(Date d, Company company, WgcpqkType type);
	
	List<WgcpylnlspcsEntity> getByDate(Date ds, Date de, Company company, WgcpqkType type, Integer cpId);

	WgcpylnlspcsEntity getByDate(Date d, Company company, WgcpqkType type, Integer cpId);

	List<WgcpylnlspcsEntity> getSumByDate(Date ds, Date de, List<Company> comps,
			WgcpqkType ylfxWgcpylnlByqZh, WgcpqkType ylfxWgcpylnlXlZh,
			Integer cpId);

	List<WgcpylnlspcsEntity> getSumByDate(Date ds, Date de,
			List<Company> comps, WgcpqkType type, Integer cpId);
}
