package com.tbea.datatransfer.model.dao.zjdl.xltb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.XLTBXL;

public interface XLTBDLDao extends AbstractReadOnlyDao<XLTBXL> {

	public List<XLTBXL> getAllXLTB();

}
