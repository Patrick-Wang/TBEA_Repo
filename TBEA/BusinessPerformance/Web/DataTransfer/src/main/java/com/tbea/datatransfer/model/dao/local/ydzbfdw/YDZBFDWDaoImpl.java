package com.tbea.datatransfer.model.dao.local.ydzbfdw;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.YDZBFDW;

@Transactional("transactionManager")
public class YDZBFDWDaoImpl extends AbstractReadWriteDaoImpl<YDZBFDW> implements
		YDZBFDWDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}