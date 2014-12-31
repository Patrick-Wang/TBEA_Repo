package com.tbea.datatransfer.model.dao.zjdl.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.YSZKPZGHDL;

public interface YSZKPZGHDLDao extends AbstractReadOnlyDao<YSZKPZGHDL> {

	public List<YSZKPZGHDL> getAllYSZKPZGHDL();
	
}
