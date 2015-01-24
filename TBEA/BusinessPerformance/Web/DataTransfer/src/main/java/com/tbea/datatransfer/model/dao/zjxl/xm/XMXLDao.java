package com.tbea.datatransfer.model.dao.zjxl.xm;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.XMXL;

public interface XMXLDao extends AbstractReadOnlyDao<XMXL> {

	public List<XMXL> getAllXM();

}
