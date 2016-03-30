package com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;



@Repository(DmdjyxDaoImpl.NAME)
@Transactional("transactionManager")
public class DmdjyxDaoImpl extends AbstractReadWriteDaoImpl<DmdjyxEntity> implements DmdjyxDao {
	public final static String NAME = "DmdjyxDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
