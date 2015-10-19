package com.tbea.ic.greet.service.account;

import com.tbea.ic.greet.model.entity.Account;

public interface AccountService {

	Account login(String name, String psw);

}
