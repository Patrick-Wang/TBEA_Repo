package com.tbea.datatransfer.model.dao.local.zbhz;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.ZBHZ;

public interface ZBHZDao extends AbstractReadWriteDao<ZBHZ> {

	public void truncateZBHZ();
}
