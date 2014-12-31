package com.tbea.datatransfer.model.dao.local.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;

public interface MRHKHZLocalDao extends AbstractReadWriteDao<MRHKHZLocal> {

	public List<MRHKHZLocal> getAllMRHKHZLocal();

	public void deleteMRHKHZLocalByQY(int qybh);

}
