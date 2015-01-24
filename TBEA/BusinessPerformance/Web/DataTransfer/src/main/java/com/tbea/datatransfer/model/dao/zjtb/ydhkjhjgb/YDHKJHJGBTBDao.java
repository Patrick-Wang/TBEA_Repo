package com.tbea.datatransfer.model.dao.zjtb.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;

public interface YDHKJHJGBTBDao extends AbstractReadOnlyDao<YDHKJHJGBBYQ> {

	public List<YDHKJHJGBBYQ> getAllYDHKJHJGB();
	
}
