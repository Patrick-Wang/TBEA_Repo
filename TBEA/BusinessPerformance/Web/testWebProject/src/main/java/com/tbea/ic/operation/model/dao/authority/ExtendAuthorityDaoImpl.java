package com.tbea.ic.operation.model.dao.authority;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Repository
@Transactional("transactionManager")
public class ExtendAuthorityDaoImpl  implements ExtendAuthorityDao{

	EntityManager entityManager;
	
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ExtendAuthority> getAuthority(Account account, int authId) {
		Query q = this.entityManager.createNativeQuery("select * from system_extend_auth_v where account_id = :id and authId=:type", ExtendAuthority.class);
		q.setParameter("id", account.getId());
		q.setParameter("type", authId);
		return q.getResultList();
	}

	@Override
	public int getAuthorityCount(Account account, int auth) {
		Query q = this.entityManager.createNativeQuery("select count(*) from system_extend_auth_v where account_id = :id and authId = :auth");
		q.setParameter("id", account.getId());
		q.setParameter("auth", auth);
		return (Integer)q.getResultList().get(0);
	}

	@Override
	public List<ExtendAuthority> getAuthority(Account account, Company comp) {
		Query q = this.entityManager.createNativeQuery("select * from system_extend_auth_v where account_id = :id and companyId = :compId", ExtendAuthority.class);
		q.setParameter("id", account.getId());
		q.setParameter("compId", comp.getId());
		return q.getResultList();
	}

	@Override
	public List<Integer> getAuthority(Account account) {
		Query q = this.entityManager.createNativeQuery("select distinct authId from system_extend_auth_v where account_id = :id");
		q.setParameter("id", account.getId());
		return q.getResultList();
	}
	
}
