package com.tbea.test.testWebProject.model.dao.transfer.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.local.HTXXLocal;

public interface HTXXLocalDao extends AbstractReadWriteDao<HTXXLocal> {
	
	public List<HTXXLocal> getAllHTXXLocal();

}
