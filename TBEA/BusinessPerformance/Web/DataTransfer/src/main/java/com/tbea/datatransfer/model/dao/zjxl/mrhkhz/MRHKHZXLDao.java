package com.tbea.datatransfer.model.dao.zjxl.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.MRHKHZXL;

public interface MRHKHZXLDao extends AbstractReadOnlyDao<MRHKHZXL> {

	public List<MRHKHZXL> getAllMRHKHZ();
	
}
