package com.tbea.ic.operation.model.dao.jygk;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YJSJZB;

public abstract class AbstractYJSJZBDao<T extends AbstractReadWriteEntity> extends AbstractReadWriteDaoImpl<T> implements YJSJZBDao {

	
	@Override
	public void update(YJSJZB zb) {
		this.persist((T)zb);
	}

	@Override
	public void insert(YJSJZB zb) {
		this.persist((T)zb);
	}

	@Override
	public YJSJZB find(Integer id) {
		return (YJSJZB) this.getById(id);
	}

}
