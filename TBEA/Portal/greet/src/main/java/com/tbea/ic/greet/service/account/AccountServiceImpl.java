
package com.tbea.ic.greet.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.dao.account.AccountDao;
import com.tbea.ic.greet.model.dao.hruser.HrUserDao;
import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.model.entity.HrUser;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements  AccountService{


	//OA
	final static String OAUrl = "http://172.28.8.119/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login&ssousername=#UN#&ssopassword=#PW#";
	//绩效管理信息平台 
	final static String JXUrl = "http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=#UN#&pass=#PW#";
	//jingyingguankong
	final static String JYGKUrl = "http://192.168.7.22/BusinessManagement/Login/validate.do?j_username=#UN#&j_password=#PW#";
	//zhihuiyinhang
	final static String ZHYHUrl = "http://km.tbea.com:8080/j_acegi_security_check?j_username=#UN#&j_password=#PW#";
	//人力
	final static String HRUrl = "http://192.168.7.76/login.jsp";
	//NC
	final static String NCUrl = "http://192.168.7.24:9083/login.jsp";

	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	HrUserDao hrUserDao;
	
	public Account validate(String name, String psw) {
		if (name != null) {
			HrUser user = hrUserDao.getByName(name);
			if (user != null) {
				Account account = accountDao.getByName(name);
				if (account == null) {
					account = new Account();
					account.setName(user.getName());
					account.setPassword(user.getPassword());
					accountDao.merge(account);
				}

				if (account != null && psw != null
						&& psw.equals(account.getPassword())) {
					return account;
				}
			}
		}
		return null;
	}

	public boolean bindSystem(Account account, String sysId, String name,
			String psw) {
		if ("1".equals(sysId)){
			account.setJxglName(name);
			account.setJxglPassword(psw);
			accountDao.merge(account);
		}else if ("3".equals(sysId)){
			account.setOAName(name);
			account.setOAPassword(psw);
			accountDao.merge(account);
		}else if ("4".equals(sysId)){
			account.setZhyhName(name);
			account.setZhyhPassword(psw);
			accountDao.merge(account);
		}else if ("5".equals(sysId)){
			account.setJygkName(name);
			account.setJygkPassword(psw);
			accountDao.merge(account);
		}else{
			return false;
		}
		return true;
	}

	public String getLoginUrl(Account account, String sysId) {
		String url="";
		switch(Integer.valueOf(sysId)){
		case 1:
			if (account.getJxglName() != null && account.getJxglPassword() != null){
				url = JXUrl.replace("#UN#", account.getJxglName()).replace("#PW#", account.getJxglPassword());
			}
			break;
		case 3:
			if (account.getOAName() != null && account.getOAPassword() != null){
				url = OAUrl.replace("#UN#", account.getOAName()).replace("#PW#", account.getOAPassword());
			}
			break;
		case 4:
			if (account.getZhyhName() != null && account.getZhyhPassword() != null){
				url = ZHYHUrl.replace("#UN#", account.getZhyhName()).replace("#PW#", account.getZhyhPassword());
			}
			break;
		case 5:
			if (account.getJygkName() != null && account.getJygkPassword() != null){
				url = JYGKUrl.replace("#UN#", account.getJygkName()).replace("#PW#", account.getJygkPassword());
			}
			break;
		case 6:
			url = HRUrl;
			break;
		case 7:
			url = NCUrl;
			break;
		}
		return url;
	}

}
