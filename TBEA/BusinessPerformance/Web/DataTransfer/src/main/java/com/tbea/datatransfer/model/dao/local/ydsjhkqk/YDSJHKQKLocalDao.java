package com.tbea.datatransfer.model.dao.local.ydsjhkqk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;

public interface YDSJHKQKLocalDao extends AbstractReadWriteDao<YDSJHKQKLocal> {

	public List<YDSJHKQKLocal> getAllYDSJHKQKLocal();

	public void deleteYDSJHKQKLocalByQY(int qybh);

}
