package com.tbea.datatransfer.model.dao.zjbyq.byqtb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.BYQTB;

public interface BYQTBDao extends AbstractReadOnlyDao<BYQTB> {

	public List<BYQTB> getAllBYQTB();

}
