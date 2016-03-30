package com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.FgcEntity;



@Repository(FgcDaoImpl.NAME)
@Transactional("transactionManager")
public class FgcDaoImpl extends AbstractReadWriteDaoImpl<FgcEntity> implements FgcDao {
	public final static String NAME = "FgcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
