package com.tbea.datatransfer.model.dao.local.fkfs.byq;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSBYQFDWLocal;

public interface FKFSBYQFDWLocalDao extends
		AbstractReadWriteDao<FKFSBYQFDWLocal> {

	public List<FKFSBYQFDWLocal> getAllFKFSBYQFDWLocal();

	public void deleteFKFSBYQFDWLocalByQY(int qybh);

}
