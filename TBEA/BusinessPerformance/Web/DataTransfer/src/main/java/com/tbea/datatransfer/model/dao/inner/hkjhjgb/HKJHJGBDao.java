package com.tbea.datatransfer.model.dao.inner.hkjhjgb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.inner.HKJHJGB;

public interface HKJHJGBDao extends AbstractReadWriteDao<HKJHJGB> {

	public void deleteHKJHJGBByNy(String ny);

}
