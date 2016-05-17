package com.tbea.ic.operation.service.extendauthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	Map<Integer, Map<Integer, List<Company>>> cacheAuth = Collections.synchronizedMap(new HashMap<Integer, Map<Integer, List<Company>>>());
	
	private List<Company> getAuthedCompaniesInternal(Account account,
			AuthType authType){
		List<Company> comps = new ArrayList<Company>();
		List<ExtendAuthority> auths = extendAuthDao.getAuthority(account, authType.ordinal());
		for (int i = 0; i < auths.size(); ++i){
			comps.add(companyManager.getBMDBOrganization().getCompany(auths.get(i).getDwxx().getId()));
		}
		return comps;
	}
	
	@Override
	public List<Company> getAuthedCompanies(Account account,
			AuthType authType) {
		Map<Integer, List<Company>> compsMap = cacheAuth.get(account.getId());
		if (null == compsMap){
			compsMap = new HashMap<Integer, List<Company>>();
			cacheAuth.put(account.getId(), compsMap);
		}

		if (!compsMap.containsKey(authType.ordinal())){
			compsMap.put(authType.ordinal(), getAuthedCompaniesInternal(account ,authType));
		}
		return compsMap.get(authType.ordinal());
	}
	
	@Override
	public Boolean hasAuthority(Account account, AuthType authType) {
		return extendAuthDao.getAuthorityCount(account, authType.ordinal()) > 0;
	}

	@Override
	public void removeCache(Account account) {
		try{
			if (null != account){
				cacheAuth.remove(account.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
