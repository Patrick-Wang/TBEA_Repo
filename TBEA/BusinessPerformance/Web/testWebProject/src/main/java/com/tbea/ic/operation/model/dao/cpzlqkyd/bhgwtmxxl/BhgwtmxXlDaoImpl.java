package com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxxl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqkyd.BhgmxByqEntity;



@Repository(BhgwtmxXlDaoImpl.NAME)
@Transactional("transactionManager")
public class BhgwtmxXlDaoImpl extends AbstractReadWriteDaoImpl<BhgmxByqEntity> implements BhgwtmxXlDao {
	public final static String NAME = "BhgwtmxXlDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
