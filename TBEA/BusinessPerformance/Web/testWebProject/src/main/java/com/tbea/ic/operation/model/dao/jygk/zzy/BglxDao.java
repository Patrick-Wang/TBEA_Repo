package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;

public interface BglxDao extends AbstractReadWriteDao<JygkZzyBglx> {
	public List<JygkZzyBglx> getViewDataList();
}
