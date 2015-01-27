package com.tbea.test.testWebProject.model.dao.blht;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.local.BLHT;

@Transactional("transactionManager")
public class BLHTDaoImpl extends AbstractReadWriteDaoImpl<BLHT> implements BLHTDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
