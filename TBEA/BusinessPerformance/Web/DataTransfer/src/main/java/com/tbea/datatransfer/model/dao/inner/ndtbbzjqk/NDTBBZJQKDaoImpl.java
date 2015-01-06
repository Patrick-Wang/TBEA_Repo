package com.tbea.datatransfer.model.dao.inner.ndtbbzjqk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.NDTBBZJQK;

@Repository
@Transactional("transactionManager")
public class NDTBBZJQKDaoImpl extends AbstractReadWriteDaoImpl<NDTBBZJQK> implements
		NDTBBZJQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
