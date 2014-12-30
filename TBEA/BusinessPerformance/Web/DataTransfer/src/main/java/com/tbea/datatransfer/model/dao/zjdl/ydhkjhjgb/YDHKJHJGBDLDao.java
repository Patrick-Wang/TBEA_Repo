package com.tbea.datatransfer.model.dao.zjdl.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.YDHKJHJGBDL;

public interface YDHKJHJGBDLDao extends AbstractReadOnlyDao<YDHKJHJGBDL> {

	public List<YDHKJHJGBDL> getAllYDHKJHJGBDL();
	
}
