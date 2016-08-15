package com.tbea.ic.operation.model.dao.cpzlqk.pdadwtjjg;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.PdAdwtjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface PdAdwtjjgDao extends AbstractReadWriteDao<PdAdwtjjgEntity> {

	List<PdAdwtjjgEntity> getAll();

	List<PdAdwtjjgEntity> getByDw(Company company);

}
