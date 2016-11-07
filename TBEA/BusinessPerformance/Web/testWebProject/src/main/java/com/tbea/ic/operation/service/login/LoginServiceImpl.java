package com.tbea.ic.operation.service.login;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.account.AccountDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.dao.userusage.UserUsageDao;
import com.tbea.ic.operation.model.entity.UserUsage;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

@Service
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService {

	@Autowired
	AccountDao accountDao;

	@Autowired
	QXGLDao qxglDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	UserUsageDao userUsageDao;
	
	public String getPassword(String usrName) {
		if (usrName != null && !usrName.isEmpty()) {
			Account account = accountDao.getAccount(usrName);
			if (null != account) {
				return account.getPassword();
			}
		}
		return null;
	}

	public Account Login(String usrName, String psw) {
		if (usrName != null && !usrName.isEmpty() && psw != null
				&& !psw.isEmpty()) {
			Account account = accountDao.getAccount(usrName);
			if (null != account) {
				if (psw.equals(account.getPassword())) {
					return account;
				}
			}
		}
		return null;
	}

	@Override
	public boolean hasCorpAuth(Account account) {
		if (account.getDwxxs().isEmpty()){
			account = accountDao.getAccount(account.getName());
		}
		Company gfgs = companyManager.getBMDBOrganization().getCompany(CompanyType.GFGS);
		for (DWXX dwxx : account.getDwxxs()){
			if (dwxx.getId() == gfgs.getId()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasSbdAuth(Account account) {
//		Company gfgs = companyManager
//				.getBMDBOrganization().getCompany(CompanyType.GFGS);
		if (account.getDwxxs().isEmpty()){
			account = accountDao.getAccount(account.getName());
		}
		
		Company sbd = companyManager.getBMDBOrganization().getCompany(CompanyType.SBDCYJT);
		for (DWXX dwxx : account.getDwxxs()){
//			if (dwxx.getId() == gfgs.getId() || dwxx.getId() == sbd.getId()){
			if (dwxx.getId() == sbd.getId()){
				return true;
			}
		}
		return false;
	}

	@Override
	public Account SSOLogin(String usrName) {
		if (usrName != null && !usrName.isEmpty()) {
			return accountDao.getAccount(usrName);
		}
		return null;
	}

	@Override
	public void logout(Account account, long creationTime,
			long lastAccessedTime, String ip) {
		LoggerFactory.getLogger("ACCOUNT").info("logout");
		UserUsage userUsage = new UserUsage();
		userUsage.setUserId(account.getId());
		userUsage.setUserName(account.getName());
		userUsage.setLoginTime(new Timestamp(creationTime));
		userUsage.setLastAccessedTime(new Timestamp(lastAccessedTime));
		userUsage.setLogoutTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		userUsage.setIp(ip);
//		LoggerFactory.getLogger("ACCOUNT").info(JSONObject.fromObject(userUsage).toString());
		userUsageDao.merge(userUsage);
	}
}
