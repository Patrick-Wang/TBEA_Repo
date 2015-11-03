package com.tbea.ic.operation.service.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.authority.ExtendAuthorityDao;
import com.tbea.ic.operation.model.entity.jygk.Account;



@Service
@Transactional("transactionManager")
public class DailyReportServiceImpl implements DailyReportService{

	@Autowired
	ExtendAuthorityDao extendAuthDao;

	@Override
	public boolean hasYszkAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, 1);
		return count > 0;
	}
	
	
}
