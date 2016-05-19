package com.tbea.ic.operation.service.extendauthority;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.authority.ExtendAuthorityDao;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Service(ExtendAuthorityServiceImpl.NAME)
@Transactional("transactionManager")
public class ExtendAuthorityServiceImpl implements ExtendAuthorityService {
	public final static String NAME = "ExtendAuthorityServiceImpl";
	
	@Autowired
	ExtendAuthorityDao extendAuthDao;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	
	@Override
	public List<Company> getAuthedCompanies(Account account,
			AuthType authType) {
		List<ExtendAuthority> auths = extendAuthDao.getAuthority(account, authType.ordinal());
		List<Company> comps = new ArrayList<Company>();
		for (int i = 0; i < auths.size(); ++i){
			comps.add(companyManager.getBMDBOrganization().getCompany(auths.get(i).getDwxx().getId()));
		}
		return comps;
	}

	@Override
	public Boolean hasAuthority(Account account, AuthType authType) {
		return extendAuthDao.getAuthorityCount(account, authType.ordinal()) > 0;
	}
}
