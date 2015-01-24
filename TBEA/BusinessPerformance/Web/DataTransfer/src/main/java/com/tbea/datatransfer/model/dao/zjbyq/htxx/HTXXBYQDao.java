package com.tbea.datatransfer.model.dao.zjbyq.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.HTXXBYQ;

public interface HTXXBYQDao extends AbstractReadOnlyDao<HTXXBYQ> {

	public List<HTXXBYQ> getAllHTXX();

}
