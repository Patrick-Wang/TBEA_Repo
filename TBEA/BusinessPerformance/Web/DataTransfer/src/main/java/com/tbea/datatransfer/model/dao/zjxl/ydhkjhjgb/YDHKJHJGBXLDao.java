package com.tbea.datatransfer.model.dao.zjxl.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;

public interface YDHKJHJGBXLDao extends AbstractReadOnlyDao<YDHKJHJGBXL> {

	public List<YDHKJHJGBXL> getAllYDHKJHJGB();
	
}
