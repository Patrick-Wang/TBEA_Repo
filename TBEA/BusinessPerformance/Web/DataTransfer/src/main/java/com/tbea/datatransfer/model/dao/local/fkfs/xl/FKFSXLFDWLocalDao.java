package com.tbea.datatransfer.model.dao.local.fkfs.xl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.FKFSXLFDWLocal;

public interface FKFSXLFDWLocalDao extends AbstractReadWriteDao<FKFSXLFDWLocal> {

	public List<FKFSXLFDWLocal> getAllFKFSXLFDWLocal();

	public void deleteFKFSXLFDWLocalByQY(int qybh);

}
