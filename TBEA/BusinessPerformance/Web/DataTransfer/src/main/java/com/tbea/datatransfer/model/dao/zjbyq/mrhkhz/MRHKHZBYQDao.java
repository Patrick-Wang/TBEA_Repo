package com.tbea.datatransfer.model.dao.zjbyq.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKHZBYQ;

public interface MRHKHZBYQDao extends AbstractReadOnlyDao<MRHKHZBYQ> {

	public List<MRHKHZBYQ> getAllMRHKHZ();

}
