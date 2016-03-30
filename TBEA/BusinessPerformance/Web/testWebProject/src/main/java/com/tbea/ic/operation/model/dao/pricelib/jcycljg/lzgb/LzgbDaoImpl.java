package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzgb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LzgbEntity;



@Repository(LzgbDaoImpl.NAME)
@Transactional("transactionManager")
public class LzgbDaoImpl extends AbstractReadWriteDaoImpl<LzgbEntity> implements LzgbDao {
	public final static String NAME = "LzgbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
