package com.tbea.datatransfer.model.dao.zjdl.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.HTXXDL;

public interface HTXXDLDao extends AbstractReadOnlyDao<HTXXDL> {

	public List<HTXXDL> getAllHTXXDL();
	
}
