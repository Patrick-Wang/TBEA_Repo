package com.tbea.datatransfer.model.dao.zjdl.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.TBBZJXXXL;

public interface TBBZJXXDLDao extends AbstractReadOnlyDao<TBBZJXXXL> {

	public List<TBBZJXXXL> getAllTBBZJXX();

}
