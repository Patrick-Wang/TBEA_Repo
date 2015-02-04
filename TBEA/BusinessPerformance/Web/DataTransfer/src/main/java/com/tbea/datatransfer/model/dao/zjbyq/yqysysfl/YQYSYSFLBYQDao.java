package com.tbea.datatransfer.model.dao.zjbyq.yqysysfl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YQYSYSFLBYQ;

public interface YQYSYSFLBYQDao extends AbstractReadOnlyDao<YQYSYSFLBYQ> {

	public List<YQYSYSFLBYQ> getAllYQYSYSFL();

}
