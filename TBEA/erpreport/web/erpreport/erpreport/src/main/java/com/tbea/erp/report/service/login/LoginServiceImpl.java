package com.tbea.erp.report.service.login;

import com.tbea.erp.report.model.dao.navigateitem.NavigateItemDaoImpl;
import com.tbea.erp.report.model.dao.navigateitem.NavigateItemDao;
import com.tbea.erp.report.model.dao.account.AccountDao;
import com.tbea.erp.report.model.dao.account.AccountDaoImpl;
import com.tbea.erp.report.model.dao.userrequest.UserRequestDao;
import com.tbea.erp.report.model.dao.userrequest.UserRequestDaoImpl;
import com.tbea.erp.report.model.dao.userusage.UserUsageDao;
import com.tbea.erp.report.model.dao.userusage.UserUsageDaoImpl;
import com.tbea.erp.report.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(LoginServiceImpl.NAME)
@Transactional("transactionManager")
public class LoginServiceImpl implements LoginService {
	@Resource(name=NavigateItemDaoImpl.NAME)
	NavigateItemDao navigateItemDao;

	@Resource(name=UserRequestDaoImpl.NAME)
	UserRequestDao userRequestDao;

	@Resource(name=UserUsageDaoImpl.NAME)
	UserUsageDao userUsageDao;

	public final static String NAME = "LoginServiceImpl";

	private final static Integer DEPRECATED = 1;
	
	@Resource(name=AccountDaoImpl.NAME)
	AccountDao accountDao;
	
	@Override
	public Account login(String usrName) {
		if (usrName != null && !usrName.isEmpty()) {
			return accountDao.getByName(usrName);
		}
		return null;
	}

//	@Override
//	public List<Authority> getAuthority(Account account) {
//		return accountDao.getAuthority(account);
//	}

	@Override
	public void logout(Account account,
			long creationTime,
			long lastAccessedTime, 
			String ip,
			List<UserRequestEntity> ures) {
		UserUsageEntity userUsage = new UserUsageEntity();
		userUsage.setUserName(account.getName() + "##" + account.getRole());
		userUsage.setLoginTime(new Timestamp(creationTime));
		userUsage.setLastAccessedTime(new Timestamp(lastAccessedTime));
		userUsage.setLogoutTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		userUsage.setIp(ip);
		userUsage = userUsageDao.merge(userUsage);
		for (UserRequestEntity ure : ures) {
			ure.setUsageId(userUsage.getId());
			userRequestDao.merge(ure);
		}
	}

	List<NavigateItemEntity> findRoots(List<NavigateItemEntity> nies){
		List<NavigateItemEntity> root = new ArrayList<NavigateItemEntity>();
		for (int i = nies.size() - 1; i >= 0; --i){
			if (nies.get(i).getParent() == null){
				root.add(0, nies.get(i));
				nies.remove(i);
			}
		}
		return root;
	}


	@Override
	public List<NavigateItemEntity> getNavigateItems(Account account) {

		List<NavigateItemEntity> nies = navigateItemDao.getItems(account);
		return buildTree(nies);
	}

//	@Override
//	public NavigateItemEntity getNavigateItem(Integer item) {
//		return navigateItemDao.getItem(item);
//	}
//
	private List<NavigateItemEntity> buildTree(List<NavigateItemEntity> nies) {
		List<NavigateItemEntity> roots = findRoots(nies);
		for (int i = roots.size() - 1; i >= 0; --i){
			if (!buildTreeItem(roots.get(i), nies)){
				roots.remove(i);
			}
		}
		return roots;
	}

	private boolean buildTreeItem(NavigateItemEntity root, List<NavigateItemEntity> nies) {
		if (root.getUrl() == null){
			for (int i = nies.size() - 1; i >= 0; --i){
				if (nies.get(i).getParent() == root.getId()){
					if (buildTreeItem(nies.get(i), nies)) {
						root.getChildren().add(0, nies.get(i));
					}
				}
			}
			return !root.getChildren().isEmpty();
		}else{
			return true;
		}
	}

}
