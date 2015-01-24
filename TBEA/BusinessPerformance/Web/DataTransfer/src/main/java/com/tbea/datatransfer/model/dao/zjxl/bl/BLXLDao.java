package com.tbea.datatransfer.model.dao.zjxl.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.BLXL;

public interface BLXLDao extends AbstractReadOnlyDao<BLXL> {

	public List<BLXL> getAllBL();
	
}
