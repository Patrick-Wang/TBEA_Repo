package com.tbea.ic.operation.service.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.authority.ExtendAuthorityDao;



@Service
@Transactional("transactionManager")
public class DailyReportServiceImpl implements DailyReportService{

	@Autowired
	ExtendAuthorityDao extendAuthDao;
	
	
}
