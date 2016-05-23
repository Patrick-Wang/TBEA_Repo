package com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk.dwxxrefclmc;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.wgcpqk.yclbfqk.DwxxRefClmcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface DwxxRefClmcDao extends AbstractReadWriteDao<DwxxRefClmcEntity> {

	List<DwxxRefClmcEntity> getByCompany(Company company);

}
