package com.tbea.datatransfer.model.dao.zjtb.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.HTXXTB;

public interface HTXXTBDao extends AbstractReadOnlyDao<HTXXTB> {

	public List<HTXXTB> getAllHTXXTB();
	
}
