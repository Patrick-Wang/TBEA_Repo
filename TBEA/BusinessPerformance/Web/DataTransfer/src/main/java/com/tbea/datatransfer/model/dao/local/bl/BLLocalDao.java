package com.tbea.datatransfer.model.dao.local.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.BLLocal;

public interface BLLocalDao extends AbstractReadWriteDao<BLLocal> {

	public List<BLLocal> getAllBLLocal();

	public void deleteBLLocalByQY(int qybh);
	
	public List<Object[]> getBLJE() throws Exception;

	public List<Object[]> getBLHKJE() throws Exception;
	
	public List<Object[]> getBLJEByQY() throws Exception;
	
	public List<Object[]> getBLHKJEByQY() throws Exception;
	
	public List<Object[]> getKHFXS() throws Exception;
	
	public List<Object[]> getFKHFXS() throws Exception;
	
	public List<Object[]> getKHFXSByQY() throws Exception;
	
	public List<Object[]> getFKHFXSByQY() throws Exception;

}
