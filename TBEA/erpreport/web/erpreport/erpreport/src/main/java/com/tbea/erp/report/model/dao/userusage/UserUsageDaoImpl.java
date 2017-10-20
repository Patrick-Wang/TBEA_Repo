package com.tbea.erp.report.model.dao.userusage;


import com.tbea.erp.report.model.entity.UserUsageEntity;
import com.speed.frame.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.erp.report.model.dao.userusage.UserUsageDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(UserUsageDaoImpl.NAME)
@Transactional("transactionManager")
public class UserUsageDaoImpl extends AbstractReadWriteDaoImpl<UserUsageEntity> implements UserUsageDao {
	public final static String NAME = "UserUsageDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
