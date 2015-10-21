
package com.tbea.ic.greet.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.dao.account.AccountDao;
import com.tbea.ic.greet.model.entity.Account;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements  AccountService{

	@Autowired
	AccountDao accountDao;
	
	public Account login(String name, String psw) {
		Account account = null;
		if (name != null){
			account = accountDao.getByName(name);
			if (account != null && psw != null && psw.equals(account.getPassword())){
				return account;
			}
		}
		return null;
	}

}
