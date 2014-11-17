package com.tbea.datatransfer.model.dao.local.xjlrb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.XJLRB;

@Repository
@Transactional("transactionManager")
public class XJLRBDaoImpl extends AbstractReadWriteDaoImpl<XJLRB> implements
		XJLRBDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}