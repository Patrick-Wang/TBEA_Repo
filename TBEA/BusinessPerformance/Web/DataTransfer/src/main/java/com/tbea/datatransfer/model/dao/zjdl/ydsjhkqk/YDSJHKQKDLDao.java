package com.tbea.datatransfer.model.dao.zjdl.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.YDSJHKQKDL;

public interface YDSJHKQKDLDao extends AbstractReadOnlyDao<YDSJHKQKDL> {

	public List<YDSJHKQKDL> getAllYDSJHKQKDL();
	
}
