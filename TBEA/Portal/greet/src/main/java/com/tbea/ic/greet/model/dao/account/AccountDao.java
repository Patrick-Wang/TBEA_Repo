package com.tbea.ic.greet.model.dao.account;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.greet.model.entity.Account;

public interface AccountDao  extends AbstractReadWriteDao<Account> {

	Account getByName(String name);

}
