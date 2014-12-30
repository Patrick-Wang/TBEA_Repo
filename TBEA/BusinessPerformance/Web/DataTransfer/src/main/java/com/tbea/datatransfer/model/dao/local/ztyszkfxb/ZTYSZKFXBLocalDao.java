package com.tbea.datatransfer.model.dao.local.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.ZTYSZKFXBLocal;

public interface ZTYSZKFXBLocalDao extends AbstractReadWriteDao<ZTYSZKFXBLocal> {

	public List<ZTYSZKFXBLocal> getAllZTYSZKFXBLocal();

	public void deleteZTYSZKFXBLocalByQY(int qybh);

}
