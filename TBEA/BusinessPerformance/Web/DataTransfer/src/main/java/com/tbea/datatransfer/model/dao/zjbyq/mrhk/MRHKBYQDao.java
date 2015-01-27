package com.tbea.datatransfer.model.dao.zjbyq.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKBYQ;

public interface MRHKBYQDao extends AbstractReadOnlyDao<MRHKBYQ> {

	public List<MRHKBYQ> getAllMRHK();

}
