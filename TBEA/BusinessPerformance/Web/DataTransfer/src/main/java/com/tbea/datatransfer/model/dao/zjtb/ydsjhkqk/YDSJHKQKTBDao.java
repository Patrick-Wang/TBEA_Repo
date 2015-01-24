package com.tbea.datatransfer.model.dao.zjtb.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YDSJHKQKBYQ;

public interface YDSJHKQKTBDao extends AbstractReadOnlyDao<YDSJHKQKBYQ> {

	public List<YDSJHKQKBYQ> getAllYDSJHKQK();

}
