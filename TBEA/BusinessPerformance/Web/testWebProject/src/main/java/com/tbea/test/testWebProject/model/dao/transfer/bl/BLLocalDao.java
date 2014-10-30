package com.tbea.test.testWebProject.model.dao.transfer.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.local.BLLocal;

public interface BLLocalDao extends AbstractReadWriteDao<BLLocal> {
	
	public List<BLLocal> getAllBLLocal();

}
