package com.tbea.datatransfer.model.dao.zjsb.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.HTXXBYQ;

public interface HTXXSBDao extends AbstractReadOnlyDao<HTXXBYQ> {

	public List<HTXXBYQ> getAllHTXX();

}
