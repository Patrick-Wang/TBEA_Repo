package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;

public interface FKFSBYQNWLocalDao extends AbstractReadWriteDao<FKFSBYQNWLocal> {

	public List<FKFSBYQNWLocal> getAllFKFSBYQNWLocal();

	public void deleteFKFSBYQNWLocalByQY(int qybh);

}
