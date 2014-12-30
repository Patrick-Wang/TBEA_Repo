package com.tbea.datatransfer.model.dao.zjdl.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.MRHKDL;

public interface MRHKDLDao extends AbstractReadOnlyDao<MRHKDL> {

	public List<MRHKDL> getAllMRHKDL();
	
}
