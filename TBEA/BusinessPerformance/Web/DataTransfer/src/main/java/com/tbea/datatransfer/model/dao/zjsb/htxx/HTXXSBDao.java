package com.tbea.datatransfer.model.dao.zjsb.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.HTXXSB;
import com.tbea.datatransfer.model.entity.zjtb.HTXXTB;

public interface HTXXSBDao extends AbstractReadOnlyDao<HTXXSB> {

	public List<HTXXSB> getAllHTXXSB();
	
}
