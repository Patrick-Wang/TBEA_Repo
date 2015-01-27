package com.tbea.ic.operation.model.dao.transfer.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.local.HTXXLocal;

public interface HTXXLocalDao extends AbstractReadWriteDao<HTXXLocal> {
	
	public List<HTXXLocal> getAllHTXXLocal();

}
