package com.tbea.datatransfer.model.dao.zjdl.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.MRHKXL;

public interface MRHKDLDao extends AbstractReadOnlyDao<MRHKXL> {

	public List<MRHKXL> getAllMRHK();
	
}
