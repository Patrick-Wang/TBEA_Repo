package com.tbea.datatransfer.model.dao.zjdl.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.BLDL;

public interface BLDLDao extends AbstractReadOnlyDao<BLDL> {

	public List<BLDL> getAllBLDL();
	
}
