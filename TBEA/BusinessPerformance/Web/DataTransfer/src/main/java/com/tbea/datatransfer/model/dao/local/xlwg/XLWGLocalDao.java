package com.tbea.datatransfer.model.dao.local.xlwg;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.XLWGLocal;

public interface XLWGLocalDao extends AbstractReadWriteDao<XLWGLocal> {

	public List<XLWGLocal> getAllXLWGLocal();

	public void deleteXLWGLocalByQY(int qybh);

}
