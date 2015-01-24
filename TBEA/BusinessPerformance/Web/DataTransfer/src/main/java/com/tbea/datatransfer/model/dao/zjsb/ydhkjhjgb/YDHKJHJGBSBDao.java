package com.tbea.datatransfer.model.dao.zjsb.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;

public interface YDHKJHJGBSBDao extends AbstractReadOnlyDao<YDHKJHJGBBYQ> {

	public List<YDHKJHJGBBYQ> getAllYDHKJHJGB();

}
