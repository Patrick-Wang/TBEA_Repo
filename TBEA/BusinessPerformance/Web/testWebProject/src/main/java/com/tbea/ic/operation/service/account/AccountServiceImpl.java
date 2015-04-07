package com.tbea.ic.operation.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.account.AccountDao;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	public boolean resetpassword(String userName, String oldPassword,
			String newPassword) {
		boolean result = false;
		if (userName != null) {
			Account account = accountDao.getAccount(userName);
			if (account != null) {
				if (oldPassword.equals(account.getPassword())) {
					account.setPassword(newPassword);
					accountDao.merge(account);
					result = true;
				}
			}
		}
		return result;
	}

}
