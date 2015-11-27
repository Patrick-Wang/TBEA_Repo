package com.tbea.ic.operation.model.dao.jygk.zzy;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChZljj;

public interface ChZljjDao  extends AbstractReadWriteDao<JygkZzyChZljj>{
	Object[] getViewData(String year, String month, String dwxxid);
}
