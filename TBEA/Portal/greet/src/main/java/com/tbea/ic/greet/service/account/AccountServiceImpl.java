package com.tbea.ic.greet.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.entity.Account;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements  AccountService{

	public Account login(String name, String psw) {
		if ("test".equals(name) && "1234".equals(psw)){
			return new Account();
		}
		return null;
	}


}
