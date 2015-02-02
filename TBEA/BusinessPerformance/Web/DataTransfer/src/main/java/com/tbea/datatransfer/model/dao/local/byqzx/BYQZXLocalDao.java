package com.tbea.datatransfer.model.dao.local.byqzx;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.BYQZXLocal;

public interface BYQZXLocalDao extends AbstractReadWriteDao<BYQZXLocal> {

	public void deleteBYQZXLocalByQY(int qybh);

}
