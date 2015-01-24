package com.tbea.datatransfer.model.dao.zjdl.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YDSJHKQKXL;

public interface YDSJHKQKDLDao extends AbstractReadOnlyDao<YDSJHKQKXL> {

	public List<YDSJHKQKXL> getAllYDSJHKQK();
	
}
