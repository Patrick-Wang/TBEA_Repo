package com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;



@Repository(JkzjDaoImpl.NAME)
@Transactional("transactionManager")
public class JkzjDaoImpl extends AbstractReadWriteDaoImpl<JkzjEntity> implements JkzjDao {
	public final static String NAME = "JkzjDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
