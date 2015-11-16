package com.tbea.ic.greet.model.dao.account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.greet.model.entity.Account;

@Repository
@Transactional("transactionManager")
public class AccountDaoImpl extends AbstractReadWriteDaoImpl<Account> implements AccountDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	public Account getByName(String name) {
		Query q = getEntityManager().createQuery("from Account where userName = :name or shortName = :name");
		q.setParameter("name", name);
		List<Account> accounts = q.getResultList();
		if (accounts.isEmpty()){
			return null;
		}else{
			return accounts.get(0);
		}
	}

}
