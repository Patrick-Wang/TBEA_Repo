package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;



@Repository(GgpDaoImpl.NAME)
@Transactional("transactionManager")
public class GgpDaoImpl extends AbstractReadWriteDaoImpl<GgpEntity> implements GgpDao {
	public final static String NAME = "GgpDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
