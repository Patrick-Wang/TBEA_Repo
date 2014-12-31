package com.tbea.datatransfer.model.dao.local.xltb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.XLTBLocal;

public interface XLTBLocalDao extends AbstractReadWriteDao<XLTBLocal> {

	public List<XLTBLocal> getAllXLTBLocal();

	public void deleteXLTBLocalByQY(int qybh);

}
