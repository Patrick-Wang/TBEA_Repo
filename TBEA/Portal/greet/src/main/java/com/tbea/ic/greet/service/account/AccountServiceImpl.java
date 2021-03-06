
package com.tbea.ic.greet.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.greet.model.dao.account.AccountDao;
import com.tbea.ic.greet.model.dao.cameluser.CamelUserDao;
import com.tbea.ic.greet.model.dao.hruser.HrUserDao;
import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.model.entity.CamelUser;
import com.tbea.ic.greet.model.entity.HrUser;
import com.tbea.ic.greet.common.AccountAlgorithm;;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements  AccountService{


	//OA
	final static String OAUrl = "http://oagroup.tbea.com.cn/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login&ssousername=#UN#&ssopassword=#PW#";
	//绩效管理信息平台 
	final static String JXUrl = "http://172.28.8.147:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=#UN#&pass=#PW#";
	//综合管理平台
	final static String ZHGKUrl = "http://172.28.8.147:8080/login.do?validate=login&ABS_SchemeName=JGYY&userId=#UN#&pass=#PW#";
	//jingyingguankong
	final static String JYGKUrl = "http://192.168.7.22/BusinessManagement/Login/validate.do?j_username=#UN#&j_password=#PW#";
	//zhihuiyinhang
	final static String ZHYHUrlInner = "http://km.tbea.com/j_acegi_security_check?j_username=#UN#&j_password=#PW#";
	final static String ZHYHUrlOuter = "http://km.tbea.com:8080/j_acegi_security_check?j_username=#UN#&j_password=#PW#";
	//人力
	final static String HRUrl = "http://192.168.7.76/login.jsp";
	//NC
	final static String NCUrl = "http://192.168.7.24:9083/login.jsp";
	//档案管理
	final static String DocumentUrl = "http://172.28.8.141/ams/index/loginHandle.jsp?usercode=#UN#&pwd=#PW#";

	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	CamelUserDao camelUserDao;
	
	public Account validate(String name, String psw) {
		if (name != null && psw != null) {
			CamelUser user = camelUserDao.getByName(name);
			if (user != null) {
				Account account = accountDao.getByName(name);
				if (account == null) {
					account = new Account();
					account.setUserName(user.getUserName());
					account.setShortName(user.getShortName());
					account.setPassword(user.getPassword());
					account = accountDao.merge(account);
				}

				if (psw.equals(account.getPassword()) || 
					psw.equals(user.getPassword())) {
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
		}else if ("6".equals(sysId)){
			account.setDocMName(name);
			account.setDocMPassword(psw);
			accountDao.merge(account);
		}else if("8".equals(sysId)){
			account.setZhglName(name);
			account.setZhglPassword(psw);
			accountDao.merge(account);
		}else{
			return false;
		}
		return true;
	}

	
	private long toLongIP(String ip) {
		String[] addrArray = ip.split("\\.");
		long num = 0;
		for (int i = 0; i < addrArray.length; i++) {
			int power = 3 - i;
			num += ((Integer.parseInt(addrArray[i]) % 256 * Math
					.pow(256, power)));
		}
		return num;
	}
	
	public String getLoginUrl(Account account, String sysId, String ip) {
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
			if (account.getZhyhName() != null
					&& account.getZhyhPassword() != null) {

				long l172From = toLongIP("172.016.000.000");
				long l172To = toLongIP("172.031.000.000");

				long l192From = toLongIP("192.168.000.000");
				long l192To = toLongIP("192.168.255.255");
				long lIp = toLongIP(ip);

				if ((lIp >= l172From && lIp <= l172To)
						|| (lIp >= l192From && lIp <= l192To)) {
					url = ZHYHUrlInner;
				} else {
					url = ZHYHUrlOuter;
				}

				url = url.replace("#UN#", account.getZhyhName()).replace(
						"#PW#", account.getZhyhPassword());
			}
			break;
		case 5:
			if (account.getJygkName() != null && account.getJygkPassword() != null){
				url = JYGKUrl.replace("#UN#", account.getJygkName()).replace("#PW#", account.getJygkPassword());
			}
			break;
		case 6:
			//url = HRUrl;
			if (account.getDocMName() != null && account.getDocMPassword() != null){
				AccountAlgorithm aa = new AccountAlgorithm();
				url = DocumentUrl.replace("#UN#", account.getDocMName()).replace("#PW#", aa.MD5(account.getDocMPassword()));
			}
			break;
		case 7:
			url = NCUrl;
			break;
		case 8:
			if (account.getZhglName() != null && account.getZhglPassword() != null){
				url = ZHGKUrl.replace("#UN#", account.getZhglName()).replace("#PW#", account.getZhglPassword());
			}
			break;
		}
		return url;
	}

	public boolean resetpassword(String userName, String oldPassword,
			String newPassword) {
		boolean result = false;
		if (userName != null) {
			Account account = accountDao.getByName(userName);
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
