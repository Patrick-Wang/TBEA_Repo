package com.tbea.ic.operation.model.dao.jygk.zbxx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZBXXDaoImpl extends AbstractReadWriteDaoImpl<ZBXX> implements ZBXXDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
