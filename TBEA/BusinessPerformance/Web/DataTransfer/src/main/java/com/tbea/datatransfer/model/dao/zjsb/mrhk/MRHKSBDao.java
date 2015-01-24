package com.tbea.datatransfer.model.dao.zjsb.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.MRHKBYQ;

public interface MRHKSBDao extends AbstractReadOnlyDao<MRHKBYQ> {

	public List<MRHKBYQ> getAllMRHK();

}
