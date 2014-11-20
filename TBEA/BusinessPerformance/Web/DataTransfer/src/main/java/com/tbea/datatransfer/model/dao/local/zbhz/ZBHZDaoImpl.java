package com.tbea.datatransfer.model.dao.local.zbhz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.ZBHZ;

@Transactional("transactionManager")
public class ZBHZDaoImpl extends AbstractReadWriteDaoImpl<ZBHZ> implements
		ZBHZDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateZBHZ() {
		String sql = "delete From ZBHZ";
		Query query = getEntityManager().createQuery(sql);
		query.executeUpdate();
	}

}