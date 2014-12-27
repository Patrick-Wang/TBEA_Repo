package com.tbea.datatransfer.model.dao.local.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.HTXXLocal;

public interface HTXXLocalDao extends AbstractReadWriteDao<HTXXLocal> {
	
	public List<HTXXLocal> getAllHTXXLocal();
	
	public void truncateHTXXLocal();
	
	public void deleteHTXXLocalByQY(int qybh);

}
