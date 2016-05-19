package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.ExtendAuthority;

public interface SystemExtendAuthDao extends AbstractReadWriteDao<ExtendAuthority> {
	List<ExtendAuthority> getDataListByAccAuthType(int account_id, int authType);
}
