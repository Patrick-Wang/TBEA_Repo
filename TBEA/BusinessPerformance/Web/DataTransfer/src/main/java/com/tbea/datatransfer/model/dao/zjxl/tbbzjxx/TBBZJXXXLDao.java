package com.tbea.datatransfer.model.dao.zjxl.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.TBBZJXXXL;

public interface TBBZJXXXLDao extends AbstractReadOnlyDao<TBBZJXXXL> {

	public List<TBBZJXXXL> getAllTBBZJXX();

}
