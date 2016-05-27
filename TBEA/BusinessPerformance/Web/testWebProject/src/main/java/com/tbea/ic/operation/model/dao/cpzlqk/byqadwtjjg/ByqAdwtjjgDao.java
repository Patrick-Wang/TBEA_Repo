package com.tbea.ic.operation.model.dao.cpzlqk.byqadwtjjg;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqAdwtjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ByqAdwtjjgDao extends AbstractReadWriteDao<ByqAdwtjjgEntity> {

	List<ByqAdwtjjgEntity> getAll();

	List<ByqAdwtjjgEntity> getByDw(Company company);

}
