package com.tbea.datatransfer.model.dao.zjxl.yqysysfl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YQYSYSFLXL;

public interface YQYSYSFLXLDao extends AbstractReadOnlyDao<YQYSYSFLXL> {

	public List<YQYSYSFLXL> getAllYQYSYSFL();

}
