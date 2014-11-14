package com.tbea.test.testWebProject.model.dao.transfer.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.local.BLLocal;

public interface BLLocalDao extends AbstractReadWriteDao<BLLocal> {

	public List<BLLocal> getAllBLLocal();

	public List<Object[]> getBLJE() throws Exception;

	public List<Object[]> getBLHKJE() throws Exception;
	
	public List<Object[]> getBLJEByQY() throws Exception;
	
	public List<Object[]> getBLHKJEByQY() throws Exception;

}
