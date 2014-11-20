package com.tbea.datatransfer.model.dao.local.xjlrb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.XJLRB;

public interface XJLRBDao extends AbstractReadWriteDao<XJLRB> {

	public void truncateXJLRB();
}
