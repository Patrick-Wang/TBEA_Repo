package com.tbea.erp.report.service.login;

import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.Authority;
import com.tbea.erp.report.model.entity.NavigateItemEntity;
import com.tbea.erp.report.model.entity.UserRequestEntity;

import java.util.List;

public interface LoginService {

	Account login(String usrName);

//	List<Authority> getAuthority(Account account);

	void logout(Account account, long creationTime, long lastAccessedTime, String ip,
                List<UserRequestEntity> ures);


    List<NavigateItemEntity> getNavigateItems(Account account);

//	NavigateItemEntity getNavigateItem(Integer item);
}
