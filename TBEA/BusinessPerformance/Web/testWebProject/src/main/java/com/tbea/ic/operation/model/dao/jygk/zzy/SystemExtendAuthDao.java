package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.ExtendAuthority;

public interface SystemExtendAuthDao {
	List<ExtendAuthority> getDataListByAccAuthType(int account_id, int authType);
}
