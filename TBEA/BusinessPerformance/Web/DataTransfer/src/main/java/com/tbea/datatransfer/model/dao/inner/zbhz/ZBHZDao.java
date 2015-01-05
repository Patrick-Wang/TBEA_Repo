package com.tbea.datatransfer.model.dao.inner.zbhz;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.inner.ZBHZ;

public interface ZBHZDao extends AbstractReadWriteDao<ZBHZ> {

	public void truncateZBHZ();
}
