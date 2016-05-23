package com.tbea.ic.operation.model.dao.jygk.zzy;



import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.DWXX;

public interface ZzyDWXXDao  extends AbstractReadWriteDao<DWXX> {

	DWXX getDwxx(int id);
}
