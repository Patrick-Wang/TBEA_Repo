package com.tbea.datatransfer.model.dao.zjdl.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;

public interface YDHKJHJGBDLDao extends AbstractReadOnlyDao<YDHKJHJGBXL> {

	public List<YDHKJHJGBXL> getAllYDHKJHJGB();
	
}
