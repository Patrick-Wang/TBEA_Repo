package com.tbea.datatransfer.model.dao.zjdl.xm;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.XMXL;

public interface XMDLDao extends AbstractReadOnlyDao<XMXL> {

	public List<XMXL> getAllXM();

}
