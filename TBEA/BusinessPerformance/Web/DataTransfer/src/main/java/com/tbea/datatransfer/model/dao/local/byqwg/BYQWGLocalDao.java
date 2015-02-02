package com.tbea.datatransfer.model.dao.local.byqwg;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.BYQWGLocal;

public interface BYQWGLocalDao extends AbstractReadWriteDao<BYQWGLocal> {

	public void deleteBYQWGLocalByQY(int qybh);

}
