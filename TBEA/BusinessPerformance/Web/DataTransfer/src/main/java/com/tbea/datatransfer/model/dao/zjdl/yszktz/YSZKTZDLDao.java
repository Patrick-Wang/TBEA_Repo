package com.tbea.datatransfer.model.dao.zjdl.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YSZKTZXL;

public interface YSZKTZDLDao extends AbstractReadOnlyDao<YSZKTZXL> {

	public List<YSZKTZXL> getAllYSZKTZ();
	
}
