package com.tbea.datatransfer.model.dao.zjxl.xltb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.XLTBXL;

public interface XLTBXLDao extends AbstractReadOnlyDao<XLTBXL> {

	public List<XLTBXL> getAllXLTB();

}
