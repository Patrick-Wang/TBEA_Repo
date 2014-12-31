package com.tbea.datatransfer.model.dao.zjdl.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.MRHKHZDL;

public interface MRHKHZDLDao extends AbstractReadOnlyDao<MRHKHZDL> {

	public List<MRHKHZDL> getAllMRHKHZDL();
	
}
