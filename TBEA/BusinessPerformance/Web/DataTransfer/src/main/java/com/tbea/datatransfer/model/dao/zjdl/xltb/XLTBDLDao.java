package com.tbea.datatransfer.model.dao.zjdl.xltb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.XLTBDL;

public interface XLTBDLDao extends AbstractReadOnlyDao<XLTBDL> {

	public List<XLTBDL> getAllXLTBDL();

}
