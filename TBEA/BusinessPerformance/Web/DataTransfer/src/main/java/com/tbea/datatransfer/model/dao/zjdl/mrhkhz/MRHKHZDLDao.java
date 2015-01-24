package com.tbea.datatransfer.model.dao.zjdl.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.MRHKHZXL;

public interface MRHKHZDLDao extends AbstractReadOnlyDao<MRHKHZXL> {

	public List<MRHKHZXL> getAllMRHKHZ();
	
}
