package com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxbyq;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqkyd.BhgmxByqEntity;



@Repository(BhgwtmxByqDaoImpl.NAME)
@Transactional("transactionManager")
public class BhgwtmxByqDaoImpl extends AbstractReadWriteDaoImpl<BhgmxByqEntity> implements BhgwtmxByqDao {
	public final static String NAME = "BhgwtmxByqDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
