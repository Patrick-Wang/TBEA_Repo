package com.tbea.ic.operation.model.dao.cpzlqk.xkadwtjjg;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XkAdwtjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XkAdwtjjgDao extends AbstractReadWriteDao<XkAdwtjjgEntity> {

	List<XkAdwtjjgEntity> getAll();

	List<XkAdwtjjgEntity> getByDw(Company company);

}
