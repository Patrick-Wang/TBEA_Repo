package com.tbea.ic.operation.model.dao.account;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.Account;

public interface AccountDao extends AbstractReadWriteDao<Account> {

	Account getAccount(String usrName);

}
