package com.tbea.datatransfer.model.dao.zjxl.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.YDSJHKQKXL;

public interface YDSJHKQKXLDao extends AbstractReadOnlyDao<YDSJHKQKXL> {

	public List<YDSJHKQKXL> getAllYDSJHKQK();
	
}
