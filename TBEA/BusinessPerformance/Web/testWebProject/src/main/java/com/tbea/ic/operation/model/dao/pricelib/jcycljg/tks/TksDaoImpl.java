package com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;



@Repository(TksDaoImpl.NAME)
@Transactional("transactionManager")
public class TksDaoImpl extends AbstractReadWriteDaoImpl<TksEntity> implements TksDao {
	public final static String NAME = "TksDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
