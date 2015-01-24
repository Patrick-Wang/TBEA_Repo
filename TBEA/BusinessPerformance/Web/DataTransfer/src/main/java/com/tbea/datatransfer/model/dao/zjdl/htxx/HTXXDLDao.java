package com.tbea.datatransfer.model.dao.zjdl.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.HTXXXL;

public interface HTXXDLDao extends AbstractReadOnlyDao<HTXXXL> {

	public List<HTXXXL> getAllHTXX();
	
}
