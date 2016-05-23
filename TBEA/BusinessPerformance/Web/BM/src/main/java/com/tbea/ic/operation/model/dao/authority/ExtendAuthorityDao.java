package com.tbea.ic.operation.model.dao.authority;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ExtendAuthorityDao extends AbstractReadWriteDao<ExtendAuthority> {

	List<ExtendAuthority> getAuthority(Account account, int authType);

	int getAuthorityCount(Account account, int authType);


}
