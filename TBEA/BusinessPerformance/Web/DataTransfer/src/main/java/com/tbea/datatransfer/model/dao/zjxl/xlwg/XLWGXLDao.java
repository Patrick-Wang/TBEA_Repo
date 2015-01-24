package com.tbea.datatransfer.model.dao.zjxl.xlwg;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.XLWGXL;

public interface XLWGXLDao extends AbstractReadOnlyDao<XLWGXL> {

	public List<XLWGXL> getAllXLWG();

}
