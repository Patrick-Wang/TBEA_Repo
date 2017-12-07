package com.tbea.erp.report.model.dao.account;


import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.Authority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;



@Repository(AccountDaoImpl.NAME)
@Transactional("transactionManager")
public class AccountDaoImpl implements AccountDao {
	public final static String NAME = "AccountDaoImpl";

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;

	@Override
	public Account getAccount(String usrName, String roleName) {
		Query q = entityManager.createNativeQuery("select distinct(role) from CUX_NAVIGATEAUTHORITY_T where role = :roleName");
		q.setParameter("roleName", roleName);
		if (!q.getResultList().isEmpty()){
			return new Account(usrName, roleName);
		}
		return null;
	}

	@Override
	public List<Authority> getAuthority(Account account) {
		Query q = entityManager.createNativeQuery("SELECT frv.responsibility_name" +
				"  FROM apps.fnd_user_resp_groups_direct fur" +
				"      ,apps.fnd_user                    fu" +
				"      ,apps.fnd_responsibility_vl       frv" +
				" WHERE fu.user_id = fur.user_id" +
				"   AND fur.responsibility_application_id = frv.application_id" +
				"   AND fur.responsibility_id = frv.responsibility_id" +
				"	AND fu.user_name = :userName");
		q.setParameter("userName", account.getName());
		List<String> authorityList = q.getResultList();
		List<Authority> authorityRet = new ArrayList<Authority>();
		List<String> comps = new ArrayList<String>();
		for (String authority : authorityList){
			Authority auth = Authority.parse(authority, null);
			authorityRet.add(auth);
			if (null != auth.getFlexValue()){
				comps.add(auth.getFlexValue());
			}
		}

		if (!comps.isEmpty()) {
			q = entityManager.createNativeQuery("SELECT ffv.flex_value ,ffv.description" +
					"  FROM apps.fnd_flex_value_sets  ffs" +
					"      ,apps.fnd_flex_values_vl   ffv" +
					" WHERE ffs.flex_value_set_id = ffv.flex_value_set_id" +
					"   and ffs.flex_value_set_name = 'TBEA_COA_CO'" +
					"	and ffv.flex_value in :comps");
			q.setParameter("comps", comps);
			List<String[]> compRet = q.getResultList();
			for (Authority auth: authorityRet) {
				if (auth.getFlexValue() != null) {
					for (Object[] comp : compRet) {
						if (auth.getFlexValue().equals(comp[0].toString())){
							auth.setCompanyName(comp[1].toString());
							break;
						}
					}
				}
			}
		}

		return authorityRet;
	}
}
