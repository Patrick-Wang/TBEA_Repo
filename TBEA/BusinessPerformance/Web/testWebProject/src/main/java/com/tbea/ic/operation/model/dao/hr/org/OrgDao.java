package com.tbea.ic.operation.model.dao.hr.org;

import com.tbea.ic.operation.model.entity.hr.Org;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

public interface OrgDao  extends AbstractReadWriteDao<Org>{

	Org getByCode(String code);

}
