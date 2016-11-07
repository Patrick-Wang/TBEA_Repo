package com.tbea.ic.operation.model.dao.userusage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.UserUsage;

@Repository
@Transactional("transactionManager")
public class UserUsageDaoImpl extends AbstractReadWriteDaoImpl<UserUsage> implements UserUsageDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	

}
