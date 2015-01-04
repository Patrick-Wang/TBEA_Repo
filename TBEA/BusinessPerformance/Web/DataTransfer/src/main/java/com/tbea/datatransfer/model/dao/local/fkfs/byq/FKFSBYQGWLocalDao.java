package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSBYQGWLocal;

public interface FKFSBYQGWLocalDao extends AbstractReadWriteDao<FKFSBYQGWLocal> {

	public List<FKFSBYQGWLocal> getAllFKFSBYQGWLocal();

	public void deleteFKFSBYQGWLocalByQY(int qybh);

}
