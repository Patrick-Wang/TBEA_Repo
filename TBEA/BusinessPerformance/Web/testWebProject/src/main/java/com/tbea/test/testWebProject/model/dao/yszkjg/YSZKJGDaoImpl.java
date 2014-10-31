package com.tbea.test.testWebProject.model.dao.yszkjg;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.test.testWebProject.model.entity.local.YSZKJG;

@Transactional("transactionManager")
public class YSZKJGDaoImpl extends AbstractReadWriteDaoImpl<YSZKJG> implements YSZKJGDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
