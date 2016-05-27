package com.tbea.ic.operation.model.dao.cpzlqk.xladwtjjg;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XlAdwtjjgEntity;



public interface XlAdwtjjgDao extends AbstractReadWriteDao<XlAdwtjjgEntity> {

	List<XlAdwtjjgEntity> getAll();

	List<XlAdwtjjgEntity> getByDw(Company company);

}
