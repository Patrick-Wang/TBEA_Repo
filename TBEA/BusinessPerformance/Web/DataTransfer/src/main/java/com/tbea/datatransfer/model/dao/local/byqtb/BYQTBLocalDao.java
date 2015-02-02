package com.tbea.datatransfer.model.dao.local.byqtb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.BYQTBLocal;

public interface BYQTBLocalDao extends AbstractReadWriteDao<BYQTBLocal> {

	public void deleteBYQTBLocalByQY(int qybh);

}
