package com.tbea.datatransfer.model.dao.zjxl.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YSZKTZXL;

public interface YSZKTZXLDao extends AbstractReadOnlyDao<YSZKTZXL> {

	public List<YSZKTZXL> getAllYSZKTZ();
	
}
