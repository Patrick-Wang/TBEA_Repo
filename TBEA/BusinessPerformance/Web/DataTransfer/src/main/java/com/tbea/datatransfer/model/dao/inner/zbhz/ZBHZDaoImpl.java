package com.tbea.datatransfer.model.dao.inner.zbhz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.ZBHZ;

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
		String sql = "truncate table ZBHZ";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}