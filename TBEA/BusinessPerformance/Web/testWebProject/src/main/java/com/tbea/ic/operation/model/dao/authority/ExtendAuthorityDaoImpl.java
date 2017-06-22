package com.tbea.ic.operation.model.dao.authority;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Repository
@Transactional("transactionManager")
public class ExtendAuthorityDaoImpl extends AbstractReadWriteDaoImpl<ExtendAuthority> implements ExtendAuthorityDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<ExtendAuthority> getAuthority(Account account, int authType) {
		Query q = this.getEntityManager().createQuery("from ExtendAuthority where account.id = :id and authType=:type");
		q.setParameter("id", account.getId());
		q.setParameter("type", authType);
		return q.getResultList();
	}

	@Override
	public int getAuthorityCount(Account account, int auth) {
		Query q = this.getEntityManager().createQuery("select count(*) from ExtendAuthority where account.id = :id and authType = :auth");
		q.setParameter("id", account.getId());
		q.setParameter("auth", auth);
		return ((Long)q.getResultList().get(0)).intValue();
	}

	@Override
	public List<ExtendAuthority> getAuthority(Account account, Company comp) {
		Query q = this.getEntityManager().createQuery("from ExtendAuthority where account.id = :id and dwxx.id = :compId");
		q.setParameter("id", account.getId());
		q.setParameter("compId", comp.getId());
		return q.getResultList();
	}

	@Override
	public List<Integer> getAuthority(Account account) {
		Query q = this.getEntityManager().createQuery("select distinct authType from ExtendAuthority where account.id = :id");
		q.setParameter("id", account.getId());
		return q.getResultList();
	}
	
}
