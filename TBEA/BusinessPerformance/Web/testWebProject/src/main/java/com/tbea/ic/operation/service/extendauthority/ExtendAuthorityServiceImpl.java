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
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
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
			comps.add(companyManager.getBMDBOrganization().getCompanyById(auths.get(i).getDwxx().getId()));
		}
		return comps;
	}
	
	private List<Company> getAuthedCompaniesInternal(Account account,
			Integer authType){
		List<Company> comps = new ArrayList<Company>();
		List<ExtendAuthority> auths = extendAuthDao.getAuthority(account, authType);
		Company compTmp = null;
		for (int i = 0; i < auths.size(); ++i){
			compTmp = companyManager.getBMDBOrganization().getCompanyById(auths.get(i).getDwxx().getId());
			if (!comps.contains(compTmp) && null != compTmp){
				comps.add(compTmp);
			}
		}
		return comps;
	}
	
	@Override
	public List<Company> getAuthedCompaniesForByqXl(Account account,
			AuthType authType) {
		List<Company> comps = this.getAuthedCompanies(account, authType);
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.addAll(comps);
		Organization org = companyManager.getVirtualGBOrg();
		Company byqcy = org.getCompanyByType(CompanyType.BYQCY);
		boolean containsAllByq = true;
		for (Company byq : byqcy.getSubCompanies()){
			if (comps.indexOf(companyManager.getBMDBOrganization().getCompanyById(byq.getId())) < 0){
				containsAllByq = false;
				break;
			}
		}
		boolean containsAllXl = true;
		Company xlcy = org.getCompanyByType(CompanyType.XLCY);
		for (Company xl : xlcy.getSubCompanies()){
			if (comps.indexOf(companyManager.getBMDBOrganization().getCompanyById(xl.getId())) < 0){
				containsAllXl = false;
				break;
			}
		}
		
		if (containsAllByq){
			compsTmp.add(byqcy);
		}
		if (containsAllXl){
			compsTmp.add(xlcy);
		}
		
		return compsTmp;
	}
	
	@Override
	public List<Company> getAuthedCompaniesForSbd(Account account,
			AuthType authType) {
		List<Company> comps = this.getAuthedCompanies(account, authType);
		List<Company> compsTmp = new ArrayList<Company>();
		compsTmp.addAll(comps);
		Organization org = companyManager.getVirtualGBOrg();
		Company byqcy = org.getCompanyByType(CompanyType.BYQCY);
		boolean containsAllByq = true;
		for (Company byq : byqcy.getSubCompanies()){
			if (comps.indexOf(companyManager.getBMDBOrganization().getCompanyById(byq.getId())) < 0){
				containsAllByq = false;
				break;
			}
		}
		boolean containsAllXl = true;
		Company xlcy = org.getCompanyByType(CompanyType.XLCY);
		for (Company xl : xlcy.getSubCompanies()){
			if (comps.indexOf(companyManager.getBMDBOrganization().getCompanyById(xl.getId())) < 0){
				containsAllXl = false;
				break;
			}
		}
		
		if (containsAllByq){
			compsTmp.add(byqcy);
		}
		if (containsAllXl){
			compsTmp.add(xlcy);
		}
		
		if (containsAllXl && containsAllByq){
			Company sbdcy = org.getCompanyByType(CompanyType.SBDCYJT);
			compsTmp.add(sbdcy);
		}
		
		return compsTmp;
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
	public List<Company> getAuthedCompanies(Account account,
			Integer authType) {
		Map<Integer, List<Company>> compsMap = cacheAuth.get(account.getId());
		if (null == compsMap){
			compsMap = new HashMap<Integer, List<Company>>();
			cacheAuth.put(account.getId(), compsMap);
		}

		if (!compsMap.containsKey(authType) || compsMap.get(authType).isEmpty()){
			compsMap.put(authType, getAuthedCompaniesInternal(account ,authType));
		}
		return compsMap.get(authType);
	}
	
	@Override
	public Boolean hasAuthority(Account account, AuthType authType) {
		return extendAuthDao.getAuthorityCount(account, authType.ordinal()) > 0;
	}
	
	@Override
	public Boolean hasAuthority(Account account, Integer authType) {
		return extendAuthDao.getAuthorityCount(account, authType) > 0;
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

	@Override
	public List<Integer> getAuths(Account account, Company comp) {
		List<ExtendAuthority> auths = extendAuthDao.getAuthority(account, comp);
		List<Integer> ret = new ArrayList<Integer>();
		for (ExtendAuthority ea : auths){
			ret.add(ea.getAuthType());
		}
		return ret;
	}
	
	@Override
	public List<Integer> getAuths(Account account) {
		return extendAuthDao.getAuthority(account);
	}
}
