package com.tbea.ic.operation.service.market;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.cb.BYQCBDao;
import com.tbea.ic.operation.model.dao.cb.XMXXDao;

@Service
@Transactional("transactionManager")
public class MarketServiceImpl implements MarketService {

	@Autowired
	private BYQCBDao byqcbDao;

	@Autowired
	private XMXXDao xmxxDao;

	
	CompanyManager companyManager;
	
	private Organization org = null;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
		org = companyManager.getPzghOrganization();
	}

}
