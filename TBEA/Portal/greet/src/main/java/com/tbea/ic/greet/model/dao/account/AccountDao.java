package com.tbea.ic.greet.model.dao.account;

import java.util.List;

import com.tbea.ic.greet.model.entity.Account;

public interface AccountDao {

	Account getByName(String name);

}
