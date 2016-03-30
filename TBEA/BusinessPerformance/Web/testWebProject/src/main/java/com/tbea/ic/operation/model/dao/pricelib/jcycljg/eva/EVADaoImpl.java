package com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;



@Repository(EVADaoImpl.NAME)
@Transactional("transactionManager")
public class EVADaoImpl extends AbstractReadWriteDaoImpl<EVAEntity> implements EVADao {
	public final static String NAME = "EVADaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
