package com.tbea.datatransfer.model.dao.inner.ydhkjhzxqk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.inner.YDHKJHZXQK;

@Transactional("transactionManager")
public class YDHKJHZXQKDaoImpl extends AbstractReadWriteDaoImpl<YDHKJHZXQK> implements
		YDHKJHZXQKDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public void truncateYDHKJHZXQK() {
		String sql = "truncate table YDHKJHZXQK";
		Query query = getEntityManager().createNativeQuery(sql);
		query.executeUpdate();
	}

}