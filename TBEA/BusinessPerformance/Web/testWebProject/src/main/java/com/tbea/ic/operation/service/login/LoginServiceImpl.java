package com.tbea.ic.operation.service.login;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.account.AccountDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
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
		Company sbd = companyManager.getBMDBOrganization().getCompany(CompanyType.SBDCYJT);
		for (DWXX dwxx : account.getDwxxs()){
//			if (dwxx.getId() == gfgs.getId() || dwxx.getId() == sbd.getId()){
			if (dwxx.getId() == sbd.getId()){
				return true;
			}
		}
		return false;
	}
}
