package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSXLGWLocal;

public interface FKFSXLGWLocalDao extends AbstractReadWriteDao<FKFSXLGWLocal> {

	public List<FKFSXLGWLocal> getAllFKFSXLGWLocal();

	public void deleteFKFSXLGWLocalByQY(int qybh);

}
