package com.tbea.ic.operation.model.dao.account;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.jygk.Account;

@Repository
@Transactional("transactionManager")
public class AccountDaoImpl extends AbstractReadWriteDaoImpl<Account> implements AccountDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	public Account getAccount(String usrName){
		EntityManager entityManager = this.getEntityManager();
		Query q = entityManager.createQuery("from Account where name = :userName");
		q.setParameter("userName", usrName);
		List<Account> account = q.getResultList();
		if (!account.isEmpty()){
			return account.get(0);
		}
		return null;
	}

}
