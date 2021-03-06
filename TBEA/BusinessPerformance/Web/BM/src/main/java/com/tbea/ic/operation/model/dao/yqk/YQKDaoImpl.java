package com.tbea.ic.operation.model.dao.yqk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.local.YQK;

@Transactional("transactionManager")
public class YQKDaoImpl extends AbstractReadWriteDaoImpl<YQK> implements YQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
