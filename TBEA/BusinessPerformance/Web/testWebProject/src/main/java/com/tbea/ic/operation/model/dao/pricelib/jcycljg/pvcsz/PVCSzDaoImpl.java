package com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PVCSzEntity;



@Repository(PVCSzDaoImpl.NAME)
@Transactional("transactionManager")
public class PVCSzDaoImpl extends AbstractReadWriteDaoImpl<PVCSzEntity> implements PVCSzDao {
	public final static String NAME = "PVCSzDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
