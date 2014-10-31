package com.tbea.test.testWebProject.model.dao.cqk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.entity.local.CQK;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

@Transactional("transactionManager")
public class CQKDaoImpl extends AbstractReadWriteDaoImpl<CQK> implements CQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
