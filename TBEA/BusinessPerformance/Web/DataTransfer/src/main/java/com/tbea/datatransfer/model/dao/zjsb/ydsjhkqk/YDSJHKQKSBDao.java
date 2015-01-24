package com.tbea.datatransfer.model.dao.zjsb.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YDSJHKQKBYQ;

public interface YDSJHKQKSBDao extends AbstractReadOnlyDao<YDSJHKQKBYQ> {

	public List<YDSJHKQKBYQ> getAllYDSJHKQK();

}
