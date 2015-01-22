package com.tbea.datatransfer.model.dao.zjsb.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.YDSJHKQKSB;
import com.tbea.datatransfer.model.entity.zjtb.YDSJHKQKTB;

public interface YDSJHKQKSBDao extends AbstractReadOnlyDao<YDSJHKQKSB> {

	public List<YDSJHKQKSB> getAllYDSJHKQKSB();

}
