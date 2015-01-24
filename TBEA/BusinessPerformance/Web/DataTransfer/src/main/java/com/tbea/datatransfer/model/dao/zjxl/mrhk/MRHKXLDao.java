package com.tbea.datatransfer.model.dao.zjxl.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.MRHKXL;

public interface MRHKXLDao extends AbstractReadOnlyDao<MRHKXL> {

	public List<MRHKXL> getAllMRHK();
	
}
