package com.tbea.datatransfer.model.dao.local.xm;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.XMLocal;

public interface XMLocalDao extends AbstractReadWriteDao<XMLocal> {

	public List<XMLocal> getAllXMLocal();

	public void deleteXMLocalByQY(int qybh);

}
