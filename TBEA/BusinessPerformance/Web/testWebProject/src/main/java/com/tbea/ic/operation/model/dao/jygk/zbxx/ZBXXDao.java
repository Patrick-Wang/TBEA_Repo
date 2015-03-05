package com.tbea.ic.operation.model.dao.jygk.zbxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.ZBXX;

public interface ZBXXDao  extends AbstractReadWriteDao<ZBXX> {

	List<ZBXX> getZbs(List<Integer> gsztzbs);

	ZBXX getZbByName(String name);

}
