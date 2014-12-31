package com.tbea.datatransfer.model.dao.zjdl.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.TBBZJXXDL;

public interface TBBZJXXDLDao extends AbstractReadOnlyDao<TBBZJXXDL> {

	public List<TBBZJXXDL> getAllTBBZJXXDL();

}
