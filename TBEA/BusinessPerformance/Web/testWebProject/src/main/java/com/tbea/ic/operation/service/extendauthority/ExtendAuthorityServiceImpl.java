package com.tbea.ic.operation.service.extendauthority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.authority.ExtendAuthorityDao;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;



@Service
@Transactional("transactionManager")
public class ExtendAuthorityServiceImpl implements ExtendAuthorityService{

	@Autowired
	ExtendAuthorityDao extendAuthDao;

	@Override
	public Boolean hasPriceLibAuthority(Account account) {
		return extendAuthDao.getAuthorityCount(account, AuthType.PriceLib.ordinal()) > 0;
	}
}
