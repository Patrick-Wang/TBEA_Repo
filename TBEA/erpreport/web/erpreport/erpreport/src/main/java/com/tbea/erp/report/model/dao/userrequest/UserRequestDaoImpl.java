package com.tbea.erp.report.model.dao.userrequest;


import com.tbea.erp.report.model.entity.UserRequestEntity;
import com.speed.frame.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.erp.report.model.dao.userrequest.UserRequestDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(UserRequestDaoImpl.NAME)
@Transactional("transactionManager")
public class UserRequestDaoImpl extends AbstractReadWriteDaoImpl<UserRequestEntity> implements UserRequestDao {
	public final static String NAME = "UserRequestDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
