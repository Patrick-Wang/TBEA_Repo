package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlZrlbEntity;



@Repository(XlZrlbDaoImpl.NAME)
@Transactional("transactionManager")
public class XlZrlbDaoImpl extends AbstractReadWriteDaoImpl<XlZrlbEntity> implements XlZrlbDao {
	public final static String NAME = "XlZrlbDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
