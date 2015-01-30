package com.tbea.ic.operation.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.account.AccountDao;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Service
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	AccountDao accountDao;
	
	public Account Login(String usrName, String psw) {
		if (usrName != null && !usrName.isEmpty()&& psw != null && !psw.isEmpty()){
			Account account = accountDao.getAccount(usrName);
			if (null != account ){
				if (psw.equals(account.getPassword())){
					return account;
				}
			}
		}
		return null;
	}
}
