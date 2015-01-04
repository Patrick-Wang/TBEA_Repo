package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSXLNWLocal;

public interface FKFSXLNWLocalDao extends AbstractReadWriteDao<FKFSXLNWLocal> {

	public List<FKFSXLNWLocal> getAllFKFSXLNWLocal();

	public void deleteFKFSXLNWLocalByQY(int qybh);

}
