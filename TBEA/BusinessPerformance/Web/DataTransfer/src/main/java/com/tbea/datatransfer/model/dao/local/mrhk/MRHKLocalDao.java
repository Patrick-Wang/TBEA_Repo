package com.tbea.datatransfer.model.dao.local.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.MRHKLocal;

public interface MRHKLocalDao extends AbstractReadWriteDao<MRHKLocal> {

	public List<MRHKLocal> getAllMRHKLocal();

	public void truncateMRHKLocal();

	public void deleteMRHKLocalByQY(int qybh);

}
