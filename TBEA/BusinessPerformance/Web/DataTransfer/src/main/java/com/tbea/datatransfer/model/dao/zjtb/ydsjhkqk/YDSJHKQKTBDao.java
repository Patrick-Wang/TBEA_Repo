package com.tbea.datatransfer.model.dao.zjtb.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.YDSJHKQKTB;

public interface YDSJHKQKTBDao extends AbstractReadOnlyDao<YDSJHKQKTB> {

	public List<YDSJHKQKTB> getAllYDSJHKQKTB();

}
