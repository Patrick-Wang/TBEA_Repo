package com.tbea.datatransfer.model.dao.zjdl.xlwg;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.XLWGDL;

public interface XLWGDLDao extends AbstractReadOnlyDao<XLWGDL> {

	public List<XLWGDL> getAllXLWGDL();

}
