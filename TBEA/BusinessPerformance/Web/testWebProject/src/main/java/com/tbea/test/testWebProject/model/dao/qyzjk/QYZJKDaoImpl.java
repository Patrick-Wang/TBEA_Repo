package com.tbea.test.testWebProject.model.dao.qyzjk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.QYZJK;

//@Repository
@Transactional("transactionManager2")
public class QYZJKDaoImpl extends AbstractReadWriteDaoImpl<QYZJK> implements
		QYZJKDao {

	@Override
	@PersistenceContext(unitName = "15DB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
