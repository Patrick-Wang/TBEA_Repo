package com.tbea.datatransfer.model.dao.zjdl.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YSZKPZGHXL;

public interface YSZKPZGHDLDao extends AbstractReadOnlyDao<YSZKPZGHXL> {

	public List<YSZKPZGHXL> getAllYSZKPZGH();
	
}
