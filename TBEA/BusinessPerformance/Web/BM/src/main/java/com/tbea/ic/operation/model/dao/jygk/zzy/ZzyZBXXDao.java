package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.ZBXX;

public interface ZzyZBXXDao  extends AbstractReadWriteDao<ZBXX> {

	List<ZBXX> getZbs(String zbidstrs);
}
